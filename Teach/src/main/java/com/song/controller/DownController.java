package com.song.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.song.pojo.Down;
import com.song.pojo.Order;
import com.song.pojo.PaymentDto;
import com.song.pojo.Reward;
import com.song.pojo.Teacher;
import com.song.service.DownService;
import com.song.service.OrderService;
import com.song.service.TeacherService;
import com.song.util.JsonResult;
import com.song.util.PayUtil;
import com.song.util.UUIDHexGenerator;
import com.song.util.XmlUtil;

@RestController
@RequestMapping("down")
public class DownController {
	
	private final String mch_id = "1511815351";//商户号
    private final String spbill_create_ip = "47.111.190.233";//终端IP
    private final String notify_url = "https://www.xurijiajiao.cn/down/paycallback";//通知地址
    private final String trade_type = "JSAPI";//交易类型
    private final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单API接口链接
    private final String key = "&key=111112222233333444445555566666Lw"; // 商户支付密钥
    private final String appid = "wxf020b9146ae3ec37";

	@Autowired
	private DownService downService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private TeacherService teacherService;
	
	/**
    *
    * @param openId
    * @param total_fee 订单总金额，单位为分。
    * @param body  商品简单描述，该字段请按照规范传递。 例：腾讯充值中心-心悦会员充值
    * @param attach    附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。 例：广州分店
    * @return
    * @throws UnsupportedEncodingException
    * @throws DocumentException
    */
   @PostMapping("pay")
   public JsonResult payment(HttpServletRequest request,String openId,String totalfee,String body,Order order) throws UnsupportedEncodingException, DocumentException {
   	String openid = openId;//用户标识
   	JSONObject JsonObject = new JSONObject() ;
       body = new String(body.getBytes("UTF-8"),"ISO-8859-1");
       String newbody = new String(body.getBytes("ISO-8859-1"),"UTF-8");//以utf-8编码放入paymentPo，微信支付要求字符编码统一采用UTF-8字符编码
       String nonce_str = UUIDHexGenerator.generate();//随机字符串
       String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
       String todays = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
       String code = PayUtil.createCode(8);
       String out_trade_no = mch_id + today + code;//商户订单号
       
       ServletContext application = request.getServletContext();
		application.setAttribute("openid", openId);
		
   	PaymentDto paymentPo = new PaymentDto();
       paymentPo.setAppid(appid);
       paymentPo.setMch_id(mch_id);
       paymentPo.setNonce_str(nonce_str);
       paymentPo.setBody(newbody);
       paymentPo.setOut_trade_no(out_trade_no);
       paymentPo.setTotal_fee(totalfee);
       paymentPo.setSpbill_create_ip(spbill_create_ip);
       paymentPo.setNotify_url(notify_url);
       paymentPo.setTrade_type(trade_type);
       paymentPo.setOpenid(openid);
       // 把请求参数打包成数组
       Map<String, Object> sParaTemp = new HashMap();
       sParaTemp.put("appid", paymentPo.getAppid());
       sParaTemp.put("mch_id", paymentPo.getMch_id());
       sParaTemp.put("nonce_str", paymentPo.getNonce_str());
       sParaTemp.put("body",  paymentPo.getBody());
       sParaTemp.put("out_trade_no", paymentPo.getOut_trade_no());
       sParaTemp.put("total_fee",paymentPo.getTotal_fee());
       sParaTemp.put("spbill_create_ip", paymentPo.getSpbill_create_ip());
       sParaTemp.put("notify_url",paymentPo.getNotify_url());
       sParaTemp.put("trade_type", paymentPo.getTrade_type());
       sParaTemp.put("openid", paymentPo.getOpenid());
       // 除去数组中的空值和签名参数
       Map sPara = PayUtil.paraFilter(sParaTemp);
       String prestr = PayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

       //MD5运算生成签名
       String mysign = PayUtil.sign(prestr, key, "utf-8").toUpperCase();
       paymentPo.setSign(mysign);
       //打包要发送的xml
       String respXml = XmlUtil.messageToXML(paymentPo);
       // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
       respXml = respXml.replace("__", "_");
       String param = respXml;
       //String result = SendRequestForUrl.sendRequest(url, param);//发起请求
       String result = PayUtil.httpRequest(url, "POST", param);
       System.out.println("请求微信预支付接口，返回 result："+result);
       // 将解析结果存储在Map中
       Map map = new HashMap();
       InputStream in=new ByteArrayInputStream(result.getBytes());
       // 读取输入流
       SAXReader reader = new SAXReader();
       Document document = reader.read(in);
       // 得到xml根元素
       Element root = document.getRootElement();
       // 得到根元素的所有子节点
       List<Element> elementList = root.elements();
       for (Element element : elementList) {
           map.put(element.getName(), element.getText());
       }
       // 返回信息
       String return_code = map.get("return_code").toString();//返回状态码
       String return_msg = map.get("return_msg").toString();//返回信息
       String result_code = map.get("result_code").toString();//返回状态码

       System.out.println("请求微信预支付接口，返回 code：" + return_code);
       System.out.println("请求微信预支付接口，返回 msg：" + return_msg);
       if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
           // 业务结果
           String prepay_id = map.get("prepay_id").toString();//返回的预付单信息
           String nonceStr = UUIDHexGenerator.generate();
           JsonObject.put("nonceStr", nonceStr);
           JsonObject.put("package", "prepay_id=" + prepay_id);
           Long timeStamp = System.currentTimeMillis() / 1000;
           JsonObject.put("timeStamp", timeStamp + "");
           String stringSignTemp = "appId=" + appid + "&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
           //再次签名
           String paySign = PayUtil.sign(stringSignTemp, key, "utf-8").toUpperCase();
           JsonObject.put("paySign", paySign);
           JsonObject.put("ordernum", out_trade_no);
           
       }
       return JsonResult.ok(JsonObject);
   }


   /**
    * 预支付时填写的 notify_url ，支付成功后的回调接口
    * @param request
    */
   @PostMapping("paycallback")
   public void paycallback(HttpServletRequest request,HttpServletResponse response) throws Exception{
   	BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
       String line = null;
       StringBuilder sb = new StringBuilder();
       while((line = br.readLine()) != null){
           sb.append(line);
       }
       br.close();
       //sb为微信返回的xml
       String notityXml = sb.toString();
       String resXml = "";
       System.out.println("接收到的报文：" + notityXml);

       Map map = XmlUtil.doXMLParse(notityXml);

       String returnCode = (String) map.get("return_code");
       if("SUCCESS".equals(returnCode)){
           //验证签名是否正确
           Map<String, String> validParams = PayUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数
           String validStr = PayUtil.createLinkString(validParams);//把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
           String sign = PayUtil.sign(validStr, key, "utf-8").toUpperCase();//拼装生成服务器端验证的签名
           //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
           if(sign.equals(map.get("sign"))){
               /**此处添加自己的业务逻辑代码start**/
        	   
        	   String code = PayUtil.createCode(8);
        	   String out_trade_no = mch_id + code;
        	   ServletContext application = request.getServletContext();
        	   String todays = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        	   String openid = (String)application.getAttribute("openid");
        	   Teacher t = teacherService.selectByOpenid(openid);
        	   Down down = new Down();
        	   down.setOpenid(openid);
        	   down.setAddtime(todays);
        	   down.setPrice("9.9");
        	   down.setOrdernum(out_trade_no);
        	   down.setName(t.getName());
        	   downService.add(down);
               
               /**此处添加自己的业务逻辑代码end**/
               //通知微信服务器已经支付成功
               resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                       + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
           }
       }else{
           resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                   + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
       }
       System.out.println(resXml);
       System.out.println("微信支付回调数据结束");
       
       BufferedOutputStream out = new BufferedOutputStream(
               response.getOutputStream());
       out.write(resXml.getBytes());
       out.flush();
       out.close();
   }
	
	
	@PostMapping("select")
	public JsonResult select(String openId,Integer type) {
		System.out.println(openId+"--"+type);
		if(type==1) {
			System.out.println(1);
			List<Down> d = downService.selectAll(openId);
			List<Order> o = orderService.selectByOpenid(openId);
			if(d.size()!=0 || o.size()!=0) {
				return JsonResult.ok();
			}else {
				return JsonResult.ok(1);
			}
		}else {
			List<Down> d = downService.selectAll(openId);
			List<Order> o = orderService.selctByPopenid(openId);
			if(d.size()!=0 || o.size()!=0) {
				return JsonResult.ok();
			}else {
				return JsonResult.ok(1);
			}
		}
	}
	
}
