package com.song.controller;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.song.pojo.Comment;
import com.song.pojo.Coupons;
import com.song.pojo.Draw;
import com.song.pojo.Order;
import com.song.pojo.Parent;
import com.song.pojo.PaymentDto;
import com.song.pojo.Refund;
import com.song.pojo.Reward;
import com.song.pojo.Teacher;
import com.song.service.CommentService;
import com.song.service.CouponService;
import com.song.service.OrderService;
import com.song.service.ParentService;
import com.song.service.PunchService;
import com.song.service.RefundService;
import com.song.service.RewardService;
import com.song.service.TeacherService;
import com.song.util.JsonResult;
import com.song.util.PayUtil;
import com.song.util.UUIDHexGenerator;
import com.song.util.WXMyConfigUtil;
import com.song.util.XmlUtil;


@RestController
@RequestMapping("order")
public class OrderController {
	
	private final String mch_id = "1511815351";//商户号
    private final String spbill_create_ip = "47.111.190.233";//终端IP
    private final String notify_url = "https://www.xurijiajiao.cn/order/paycallback";//通知地址
    private final String trade_type = "JSAPI";//交易类型
    private final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单API接口链接
    private final String key = "&key=111112222233333444445555566666Lw"; // 商户支付密钥
    private final String appid = "wxf020b9146ae3ec37";
 
    @Autowired
    private OrderService orderService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private RefundService refundService;
    @Autowired
    private RewardService rewardService;
    @Autowired
    private PushController pushController;
    
    
    //返回工作台
    @PostMapping("selectByTid")
    public JsonResult selectByTid(int tid) {
    	List<Order> o = orderService.selectbytid(tid);
//    	for(int i=0;i<o.size();i++) {
//    		Integer count = pushService.selectCount(o.get(i).getOrdernum());
//    		if(count.toString()!=o.get(i).getNum()) {
//    			return JsonResult.ok(o.get(i));
//    		}else {
//    		}
//    	}
    	return JsonResult.ok(o);
    }
    
    
    ////家长删订单
    @PostMapping("delete")
    public JsonResult delete(int id) {
    	orderService.delete(id);
    	return JsonResult.ok();
    }
    
    //查找我的老师
    @PostMapping("selectTeaByPar")
    public JsonResult selectTeaByPar(String openid) {
    	Set<Teacher> t = orderService.selectTeaByPar(openid);
    	return JsonResult.ok(t);
    }
    
    //查找我的学生
    @PostMapping("selectParByTea")
    public JsonResult selectParByTea(int tid) {
    	Set<Order> os = orderService.selectParByTea(tid);
    	return JsonResult.ok(os);
    }
    
    //返回待支付订单
    @PostMapping("selectByStatus")
    public JsonResult selectByStatus(String openid) {
    	List<Order> os = orderService.selectByStatus(openid);
    	return JsonResult.ok(os);
    }
    
    //返回支付订单
    @PostMapping("selectAll")
    public JsonResult selectAll(String openid) {
    	List<Order> os = orderService.selectAll(openid);
    	return JsonResult.ok(os);
    }
    
    //通过订单号查询
    @PostMapping("selectByOrderNum")
    public JsonResult selectByOrderNum(String ordernum) {
    	Order os = orderService.selectByOrder(ordernum);
    	return JsonResult.ok(os);
    }
    
    //返回未评价的订单
    @PostMapping("selectByComment")
    public JsonResult selectByComment(String openid) {
    	List<Order> os = orderService.selectByComment(openid);
    	if (os!=null) {
    		return JsonResult.ok(os);
		}
    	return JsonResult.ok();
    }
    
    //返回已评价的订单
    @PostMapping("selectByComment2")
    public JsonResult selectByComment2(String openid) {
    	List<Order> os = orderService.selectByComment2(openid);
    	if(os!=null) {
    		for(int i=0;i<os.size();i++) {
    			String ordernum = os.get(i).getOrdernum();
    			List<Comment> o = commentService.selectByOrdernum(ordernum);
    			o.get(i).setBody(o.get(i).getOrder().getBody());
    			o.get(i).setTimage(o.get(i).getOrder().getImage());
    			o.get(i).setTname(o.get(i).getOrder().getName());
    			o.get(i).setTotalnum(o.get(i).getOrder().getTotalnum());
    			o.get(i).setNum(o.get(i).getOrder().getNum());
    			o.get(i).setOtypes(o.get(i).getOrder().getTypes());
    			o.get(i).setPrice(o.get(i).getOrder().getPrice());
    			return JsonResult.ok(o);
    		}
    	}
    	return JsonResult.ok();
    }
    
    
    
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
    @RequestMapping("pay")
    public JsonResult payment(String orderpar,HttpServletRequest request,String openId,String totalfee,String body,Order order,String shareId,Integer cid,String popenid) throws UnsupportedEncodingException, DocumentException {
    	System.out.println("==========================="+shareId);
    	System.out.println(order.toString());
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
		application.setAttribute("ordernum", out_trade_no);
		application.setAttribute("openid", openId);
		application.setAttribute("tid", order.getTid());
		application.setAttribute("shareid", shareId);
		application.setAttribute("cid", cid);
		application.setAttribute("popenid", popenid);
		
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
            
            if(order.getTid()!=null) {
            	Teacher t = teacherService.selectById(order.getTid(),"");
                order.setOrdernum(out_trade_no);
                order.setAddtime(todays);
                order.setOpenId(openid);
                order.setBody(newbody);
                order.setTotalfee(totalfee);
                order.setStatus("待支付");
                order.setComment("否");
                order.setImage(t.getImage());
                order.setName(t.getName());
                order.setOrderpar(orderpar);
                orderService.insert(order);
            }else {
            	order.setOrdernum(out_trade_no);
                order.setAddtime(todays);
                order.setOpenId(openid);
                order.setBody(newbody);
                order.setTotalfee(totalfee);
                order.setStatus("待支付");
                order.setComment("否");
                orderService.insert(order);
            }
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

            	//修改订单状态
                ServletContext application = request.getServletContext();
                String ordernum = (String)application.getAttribute("ordernum");
                orderService.updateStatus("已支付",ordernum);
                orderService.updateTimeStatus("进行中", ordernum);
                
                Order o = orderService.selectByOrderNum(ordernum);
                Parent p = parentService.selectByOpenid(o.getOpenId());
                
                String openid = (String)application.getAttribute("openid");
//                String formId = (String)application.getAttribute("formId");
                String shareid = (String) application.getAttribute("shareid");
                String popenid = (String)application.getAttribute("popenid");
                if(shareid!=null) {
                	
                	List<Order> os = orderService.selectByOpenid(openid);
                	//判断当前下单人是否第一次下单
                	if(os.size()==1) {
                		System.out.println(os.size());
                		Reward r = new Reward();
                		//插入奖励表
                		r.setOpenid(os.get(0).getPopenid());
                		r.setMoney(String.valueOf(Double.valueOf(os.get(0).getTotalfee())*0.05/100));
                		r.setOrdernum(ordernum);
                		r.setShareId(shareid);
                		r.setStatus("0");
                		rewardService.add(r);
                		//推荐人获得金额
//                		Parent pt = parentService.selectByOnId("是", shareid);
//                		Teacher tr = teacherService.selectByOnId("是", shareid);
//                		if(pt!=null) {
//                			parentService.updateMoney(pt.getMoney()+Integer.valueOf(os.get(0).getTotalprice())*0.05, shareid);
//                		}else if(tr!=null) {
//                			teacherService.updateOnId(tr.getMoney()+Integer.valueOf(os.get(0).getTotalprice())*0.05, shareid);
//                		}
                	}
                }
               
                //使用了优惠券，改优惠券状态为已使用
                Integer cid = (Integer) application.getAttribute("cid");
                if(cid!=null) {
                	couponService.updateStatus(cid);
                }
                
//                pushController.pushOrder(popenid, o.getName(), o.getBody(),String.valueOf(Double.valueOf(o.getTotalfee())/100), o.getOrdernum(), o.getAddtime());
//                pushController.pushOrder("oY2Uc0f1SEvcTPN4GKOQYfuMuFP0", o.getName(), o.getBody(),String.valueOf(Double.valueOf(o.getTotalfee())/100), o.getOrdernum(), o.getAddtime());
//                pushController.newOrder("oY2Uc0f1SEvcTPN4GKOQYfuMuFP0", "有用户下单,请查看。", o.getAddtime());
                pushController.sendPay("oY2Uc0f1SEvcTPN4GKOQYfuMuFP0", String.valueOf(Double.valueOf(o.getTotalfee())/100), o.getBody(), o.getOrdernum());
                pushController.paypush("有一位家长学生希望由您来进行授课!", o.getOpenId(), o.getBody(), "时间请查看详情", o.getAddr(), "待确认",o.getId(),p.getId());
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
    
    
    
    //完成待支付
    @RequestMapping("payagain")
    public JsonResult payagain(HttpServletRequest request,String openId,String totalfee,String body,Order order,Integer shareId,Integer cid) throws UnsupportedEncodingException, DocumentException {
    	String openid = openId;//用户标识
    	JSONObject JsonObject = new JSONObject() ;
        body = new String(body.getBytes("UTF-8"),"ISO-8859-1");
        String newbody = new String(body.getBytes("ISO-8859-1"),"UTF-8");//以utf-8编码放入paymentPo，微信支付要求字符编码统一采用UTF-8字符编码
        String nonce_str = UUIDHexGenerator.generate();//随机字符串
        
        ServletContext application = request.getServletContext();
		application.setAttribute("ordernum", order.getOrdernum());
		application.setAttribute("openid", openId);
		application.setAttribute("tid", order.getTid());
		application.setAttribute("shareid", shareId);
		application.setAttribute("cid", cid);
        
    	PaymentDto paymentPo = new PaymentDto();
        paymentPo.setAppid(appid);
        paymentPo.setMch_id(mch_id);
        paymentPo.setNonce_str(nonce_str);
        paymentPo.setBody(newbody);
        paymentPo.setOut_trade_no(order.getOrdernum());
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
        }
        return JsonResult.ok(JsonObject);
    }
    
    
    /**
     * 
     * 退款
     */
    @PostMapping("refund")
    public JsonResult refund(String openid,String ordernum,Integer money) throws Exception{
    	System.out.println();
    	
    	Order order = orderService.selectByOrderNum(ordernum);
    	if(order!=null && order.getStatus().equals("退款中")) {
	    	Map<String, String> data = new HashMap<String, String>();
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	    	String now = dateFormat.format(new Date());
	    	String out_refund_no = now + "wxrefund";
	    	String out_trade_no = order.getOrdernum();
	    	String total_fee = String.valueOf(order.getTotalfee());
	    	String nonce_str = UUIDHexGenerator.generate();//随机字符串
	    	data.put("out_refund_no", out_refund_no);
	        data.put("out_trade_no", out_trade_no);
	        data.put("total_fee", total_fee);
	        data.put("refund_fee", String.valueOf(money));
	        System.out.println("hHHHHHHHHHHHHHHHHHHHHH"+money);
    	
	        WXMyConfigUtil config = new WXMyConfigUtil();
	        WXPay wxpay = new WXPay(config);
	        data.put("appid", appid);
	        data.put("mch_id", mch_id);
	        data.put("nonce_str", nonce_str);
	        Map sPara = PayUtil.paraFilters(data);
	        String prestr = PayUtil.createLinkString(sPara);
	        data.put("sign", PayUtil.sign(prestr, key, "utf-8").toUpperCase());
	        
	        Map<String, String> resp = wxpay.refund(data);
	        System.err.println(resp);
	        String return_code = resp.get("return_code");   //返回状态码
	        String return_msg = resp.get("return_msg");     //返回信息
	        System.out.println(return_code);
	        System.out.println(return_msg);
	        
	        if("SUCCESS".equals(return_code)) {
	        	String result_code = resp.get("result_code");       //业务结果
	            String err_code_des = resp.get("err_code_des");     //错误代码描述
	        	if("SUCCESS".equals(result_code)) {
	        		
	        		//更改退款状态
	        		refundService.update(String.valueOf(money),ordernum);
	        		//更改订单状态
	        		orderService.updateStatus("已退款", ordernum);
	        		orderService.updateTimeStatus("已取消", ordernum);
	        		
	        		
	        		return JsonResult.ok("退款成功");
	        	}else {
	        		return JsonResult.ok(err_code_des);
	        	}
	        }else {
	        	return JsonResult.ok(return_msg);
	        }
    	}
    	return JsonResult.ok("无此订单");
    }
    
    
    
    
    /**
     * 提现
     */
    @PostMapping("draw")
    public JsonResult draw(Draw draw) {
    	System.out.println(draw.getOpenid());
    	System.out.println(draw.getMoney());
    	SortedMap<String, Object> params = new TreeMap<String, Object>();
    	String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String todays = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String code = PayUtil.createCode(8);
    	String out_trade_no = mch_id + today + code;
    	//微信公众号的appid
    	params.put("mch_appid", appid);
    	params.put("mchid", mch_id);
    	params.put("nonce_str", today+code);//随机数
    	params.put("partner_trade_no", out_trade_no);//订单号
    	params.put("openid", draw.getOpenid());
    	params.put("check_name", "NO_CHECK");//是否验证真实姓名呢
    	params.put("openid", draw.getOpenid());
    	params.put("amount", draw.getMoney());//企业付款金额，单位为分
    	params.put("desc", "提现");
    	params.put("desc", "47.111.190.233");//调用接口的机器Ip地址 设为固定
    	Map sPara = PayUtil.paraFilter(params);
        String prestr = PayUtil.createLinkString(sPara);
        //签名
        params.put("sign", PayUtil.sign(prestr, key, "utf-8").toUpperCase());
        //map转xml
        String xml = XmlUtil.map2Xml(params);
        try {
        	//请求
        	String result = PayUtil.httpRequest("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers", "POST", xml);
        	System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return JsonResult.ok();
    }
    
    
    
    
    
    
    
	
}
