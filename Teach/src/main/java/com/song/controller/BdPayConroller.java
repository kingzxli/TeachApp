package com.song.controller;


import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.BaiDuBizInfo;
import com.song.pojo.BaiDuMsg;
import com.song.pojo.BaiDuOrderInfo;
import com.song.pojo.BaiDuRefund;
import com.song.pojo.BaiDuSyncOrderStatus;
import com.song.pojo.BdOrder;
import com.song.pojo.Order;
import com.song.pojo.Teacher;
import com.song.service.BdOrderService;
import com.song.service.OrderService;
import com.song.service.TeacherService;
import com.song.util.BaiDuConfig;
import com.song.util.HttpClientUtil;
import com.song.util.JsonResult;
import com.song.util.JsonUtil;
import com.song.util.NuomiApiException;
import com.song.util.NuomiSignature;
import com.song.util.StreamUtil;

@RestController
@RequestMapping("bdpay")
public class BdPayConroller {
	
	@Autowired
	BdOrderService bdorderService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	OrderService orderService;
	
	@RequestMapping("pay")
	public JsonResult pay(String totalfee,String title,String topenid,String orderpar,HttpServletRequest request,String openId,String body,Order order,String popenid) throws Exception {
		String todays = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todays);
		//百度收银台创建
		BaiDuOrderInfo orderInfo = new BaiDuOrderInfo();
		String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String ordernum = today+BaiDuConfig.DEAL_ID;
		orderInfo.setAppKey(BaiDuConfig.APP_KEY_PAY);
		orderInfo.setDealId(BaiDuConfig.DEAL_ID);
		orderInfo.setDealTitle(title);
		orderInfo.setTotalAmount(totalfee);
		orderInfo.setTpOrderId(ordernum);
		orderInfo.setSignFieldsRange("1");

		//百度支付签名
		Map<String, String> data = new HashMap<>();
		data.put("appKey", orderInfo.getAppKey());
		data.put("dealId", BaiDuConfig.DEAL_ID);
		data.put("totalAmount", orderInfo.getTotalAmount());
		data.put("tpOrderId", orderInfo.getTpOrderId());

		//签名使用的类是百度签名Demo里的，自行下载即可
		String sign = NuomiSignature.genSignWithRsa(data, BaiDuConfig.PRIVATE_KEY);
		if (StringUtils.isBlank(sign)) {
			JsonResult jsonResult = new JsonResult();
			jsonResult.setStatus(108);
			jsonResult.setMsg("签名错误");
			return jsonResult;
		}
		orderInfo.setRsaSign(sign);
		BaiDuBizInfo bizInfo = new BaiDuBizInfo();
		bizInfo.setAppKey(BaiDuConfig.APP_KEY);
		bizInfo.setDealld(BaiDuConfig.DEAL_ID);
		bizInfo.setTotalAmount(totalfee);
		bizInfo.setTpOrderId(ordernum);
		
		orderInfo.setBaiDuBizInfo(bizInfo);
		BdOrder bo = new BdOrder();
		bo.setTpOrderId(ordernum);
		bo.setTotalMoney(order.getTotalprice());
		bo.setCount(order.getTotalnum());
		bo.setUnitPrice(order.getPrice());
		bo.setPayMoney(totalfee);
		bo.setPayTime(String.valueOf(date.getTime()));
		bo.setStatus("1");
		bo.setRsaSign(sign);
		System.out.println(date.getTime());
		bdorderService.insert(bo);
		
		
		
		//插入订单表
		if(order.getTid()!=null) {
        	Teacher t = teacherService.selectById(order.getTid(),"");
            order.setOrdernum(ordernum);
            order.setAddtime(todays);
            order.setOpenId(openId);
            order.setBody(title);
            order.setTotalfee(totalfee);
            order.setStatus("待支付");
            order.setComment("否");
            order.setImage(t.getImage());
            order.setName(t.getName());
            order.setOrderpar(orderpar);
            order.setIsbd(1);
            orderService.insert(order);
        }else {
        	order.setOrdernum(ordernum);
            order.setAddtime(todays);
            order.setOpenId(openId);
            order.setBody(title);
            order.setTotalfee(totalfee);
            order.setStatus("待支付");
            order.setComment("否");
            order.setIsbd(1);
            orderService.insert(order);
        }
		
		
		return JsonResult.ok(orderInfo);
	}
	
	
	//支付订单回调
	@PostMapping("/payCallback")
	public String payCallback(HttpServletRequest request, HttpServletResponse response
	                          ) throws IOException {
	    /**
		代码主体根据自己项目逻辑编写，毕竟每个项目都不一样，这里值得注意的是：
		百度小程序不同于微信和支付宝，微信支付宝退款啥的，只需要传入一个商户订单号或者平台订单号即可，百度我看文档说是需要两个，两个都是必须，所以百度平台订单号如果没有保存逻辑的需要保存，百度传回的userId亦是，这都是后面退款需要用到的
		*/
		String tpOrderId = request.getParameter("tpOrderId");
		String status = request.getParameter("status");
		String orderId = request.getParameter("orderId");
		String userId = request.getParameter("userId"); 
		String unitPrice = request.getParameter("unitPrice");
		String count = request.getParameter("count");
		String totalMoney = request.getParameter("totalMoney");
		String payMoney = request.getParameter("payMoney");
		String promoMoney = request.getParameter("promoMoney");
		String hbMoney=request.getParameter("hbMoney");
		String hbBalanceMoney=request.getParameter("hbBalanceMoney");
		String giftCardMoney=request.getParameter("giftCardMoney");
		String dealId=request.getParameter("dealId");
		String payTime=request.getParameter("payTime");
		String promoDetail=request.getParameter("promoDetail");
		String payType=request.getParameter("payType");
		String partnerId=request.getParameter("partnerI");
		String returnData=request.getParameter("returnData");
		String rsaSign=request.getParameter("rsaSign");
		
		BdOrder bdorder = new BdOrder();
		bdorder.setTpOrderId(tpOrderId);
		bdorder.setStatus(status);
		bdorder.setOrderId(orderId);
		bdorder.setUserId(userId);
		bdorder.setUnitPrice(unitPrice);
		bdorder.setCount(count);
		bdorder.setTotalMoney(totalMoney);
		bdorder.setPayMoney(payMoney);
		bdorder.setPromoMoney(promoMoney);
		bdorder.setHbMoney(hbMoney);
		bdorder.setHbBalanceMoney(hbBalanceMoney);
		bdorder.setGiftCardMoney(giftCardMoney);
		bdorder.setDealId(dealId);
		bdorder.setPayTime(payTime);
		bdorder.setPromoDetail(promoDetail);
		bdorder.setPayType(payType);
		bdorder.setPartnerId(partnerId);
		bdorder.setReturnData(returnData);
		bdorder.setRsaSign(rsaSign);
		bdorderService.update(bdorder);
		
		orderService.updateStatus("已支付",tpOrderId);
        orderService.updateTimeStatus("进行中", tpOrderId);
		
		/**
		返回值也必须按照官方文档规定来。
		百度小程序订单状态也比其他平台要多一种：
		已付款：用户付款成功，成功调用成功回调，但支付金额不会进入公司账户
		已消费：使已付款的订单金额，进入到公司账户
		
		下面的示例返回就是核销当前订单，使之付款金额进入公司账户，其余的自己看文档
		*/
	    return "{\"errno\":0,\"msg\":\"success\",\"data\":{\"isConsumed\":2}}";
	}
	
	
	//完成待支付
    @RequestMapping("payagain")
    public JsonResult payagain(HttpServletRequest request,String openId,String totalfee,String body,Order order) throws  Exception {
    	String todays = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todays);
		//百度收银台创建
		BaiDuOrderInfo orderInfo = new BaiDuOrderInfo();
		String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String ordernum = order.getOrdernum();
		orderInfo.setAppKey(BaiDuConfig.APP_KEY_PAY);
		orderInfo.setDealId(BaiDuConfig.DEAL_ID);
		orderInfo.setDealTitle(body);
		orderInfo.setTotalAmount(totalfee);
		orderInfo.setTpOrderId(ordernum);
		orderInfo.setSignFieldsRange("1");
	
		//百度支付签名
		Map<String, String> data = new HashMap<>();
		data.put("appKey", orderInfo.getAppKey());
		data.put("dealId", BaiDuConfig.DEAL_ID);
		data.put("totalAmount", orderInfo.getTotalAmount());
		data.put("tpOrderId", orderInfo.getTpOrderId());
	
		//签名使用的类是百度签名Demo里的，自行下载即可
		String sign = NuomiSignature.genSignWithRsa(data, BaiDuConfig.PRIVATE_KEY);
		if (StringUtils.isBlank(sign)) {
			JsonResult jsonResult = new JsonResult();
			jsonResult.setStatus(108);
			jsonResult.setMsg("签名错误");
			return jsonResult;
		}
		orderInfo.setRsaSign(sign);
		BaiDuBizInfo bizInfo = new BaiDuBizInfo();
		bizInfo.setAppKey(BaiDuConfig.APP_KEY);
		bizInfo.setDealld(BaiDuConfig.DEAL_ID);
		bizInfo.setTotalAmount(totalfee);
		bizInfo.setTpOrderId(ordernum);
		
		orderInfo.setBaiDuBizInfo(bizInfo);
		return JsonResult.ok(orderInfo);
	}
	
	
	
	/**
	 * 取消核销接口，已经支付核销过的订单不允许直接退款，想要发起退款时，需要先调用此接口进行取消核销
	 *
	 * @param orderId
	 * @param userId
	 * @return
	 */
	private static String syncOrderStatus(String orderId, String userId) {
	    try {
	        BaiDuSyncOrderStatus rest = new BaiDuSyncOrderStatus();
	        rest.setAppKey(BaiDuConfig.APP_KEY_PAY);
	        rest.setOrderId(orderId);
	        rest.setUserId(userId);
	        rest.setType("3");
	        rest.setMethod("nuomi.cashier.syncorderstatus");

	        Map<String, String> data = new HashMap<>();
	        data.put("orderId", rest.getOrderId());
	        data.put("method", rest.getMethod());
	        data.put("userId", rest.getUserId());
	        data.put("type", rest.getType());
	        data.put("appKey", rest.getAppKey());

	        rest.setRsaSign(NuomiSignature.genSignWithRsa(data, BaiDuConfig.PRIVATE_KEY));

	        System.out.println(JsonUtil.objectToJson(rest));


	        String postJson = HttpClientUtil.doPost("https://nop.nuomi.com/nop/server/rest", JsonUtil.toMap(JsonUtil.objectToJson(rest), String.class, String.class));
	        System.out.println("postJson1------------>" + postJson);
	        BaiDuMsg resultInfo = (BaiDuMsg)JsonUtil.jsonToPojo(postJson, BaiDuMsg.class);
	        if ("success".equals(resultInfo.getMsg())) {
	        	System.out.println("取消核销成功");
	            return "success";
	        }
	    } catch (NuomiApiException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	//申请退款
	//后台审核调用此接口
	@PostMapping("bdrefund")
	public static BaiDuMsg applyOrderRefund(String orderId, String userId, String tpOrderId) {
	    try {
	        //核销的订单需要先取消核销 调用上面的核销接口，成功核销才可以申请退款
	        String result = syncOrderStatus(orderId, userId);
	        if (StringUtils.isBlank(result)) {
	        	System.out.println("需要先取消审核");
	            return null;
	        }

	        BaiDuRefund refund = new BaiDuRefund();
	        refund.setMethod("nuomi.cashier.applyorderrefund");
	        refund.setOrderId(orderId);
	        refund.setUserId(userId);
	        refund.setRefundType(1);
	        refund.setRefundReason(URLEncoder.encode("申请退款", "GBK"));
	        refund.setTpOrderId(tpOrderId);
	        refund.setAppKey(BaiDuConfig.APP_KEY_PAY);

	        Map<String, String> data = new HashMap<>();
	        data.put("method", refund.getMethod());
	        data.put("orderId", refund.getOrderId());
	        data.put("userId", refund.getUserId());
	        data.put("refundType", refund.getRefundType().toString());
	        data.put("refundReason", refund.getRefundReason());
	        data.put("tpOrderId", refund.getTpOrderId());
	        data.put("appKey", refund.getAppKey());

	        refund.setRsaSign(NuomiSignature.genSignWithRsa(data, BaiDuConfig.PRIVATE_KEY));
			//参数也是得以map形式传递，这里当你发送请求，然后判断他的返回值是否正确即可，具体怎么用具体情况具体对待
	        String postJson = HttpClientUtil.doPost("https://nop.nuomi.com/nop/server/rest", JsonUtil.toMap(JsonUtil.objectToJson(refund), String.class, String.class));
	        BaiDuMsg bdmsg = JsonUtil.jsonToPojo(postJson, BaiDuMsg.class);
	        if (bdmsg != null && "success".equals(bdmsg.getMsg())) {
	            return bdmsg;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	@PostMapping("/refundCheckCallback")
	public String refundCheckCallback(HttpServletRequest request, HttpServletResponse response,
	                                  @RequestParam("tpOrderId") String tpOrderId,
	                                  @RequestParam("refundBatchId") String refundBatchId,
	                                  @RequestParam("orderId") String orderId,
	                                  @RequestParam("refundPayMoney")String refundPayMoney) {
		
		System.out.println("errno--------------"+request.getParameter("errno"));
		System.out.println("success--------------"+request.getParameter("success"));
		System.out.println("data--------------"+request.getParameter("data").toString());
		System.out.println("tpOrderId--------------"+tpOrderId);
		System.out.println("refundBatchId--------------"+refundBatchId);
		System.out.println("errno--------------"+orderId);
	    if (true) {
		    //可退款
		    return "{\"errno\":0,\"msg\":\"success\",\"data\":{\"auditStatus\":1, \"calculateRes\":{\"refundPayMoney\":" + refundPayMoney + "}}}";
		}
	    return "{\"errno\":0,\"msg\":\"success\",\"data\":{\"auditStatus\":1, \"calculateRes\":{\"refundPayMoney\":" + refundPayMoney + "}}}";
	}
	
	
	
	@PostMapping("/refundCallback")
	public String refundCallback(HttpServletRequest request, HttpServletResponse response,
	                             @RequestParam("tpOrderId") String tpOrderId,
	                             @RequestParam("refundBatchId") String refundBatchId,
	                             @RequestParam("orderId") String orderId,
	                             @RequestParam("refundStatus") String refundStatus) {
	    response.setHeader("Content-type", "text/html;charset=UTF-8");
	    try {
	        String reqParams = StreamUtil.readText(request.getInputStream());
	        System.out.println("百度小程序退款回调结果：" + reqParams);
	        System.out.println("tpOrderId = " + tpOrderId);
	        System.out.println("refundBatchId = " + refundBatchId);
	        System.out.println("orderId = " + orderId);
			//退款状态：1：退款成功，2：退款失败
	        System.out.println("refundStatus = " + refundStatus);

	        //代码主体略

	        return "{\"errno\":0,\"msg\":\"success\",\"data\":{}}";
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
}
