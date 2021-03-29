package com.song.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.song.util.HttpClientUtil;
import com.song.util.WXTmplMsgUtils;
import net.sf.json.JSONObject;


@RestController
@RequestMapping("push")
public class PushController {
	
	
	private static String PUSH_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	private static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	private static String APP_ID = "wx2da6d0a84363b015";
	private static String SECRET = "f5f06cfa60b77572289c52ff238ba049";
	
    
//    客户填写“帮我选师”后，管理者能够收到通知提醒
    public JSONObject sendMpMessage(String openid,String name,String phone,String address,String addtime,String trial,String id,String stuid){
		JSONObject result = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONObject mp_template_msg = new JSONObject();
		JSONObject miniprogram = new JSONObject();
		// 获取token
	    String accesstoken = "https://api.weixin.qq.com/cgi-bin/token";
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("grant_type", "client_credential");
	    m.put("appid", "wxf020b9146ae3ec37");
	    m.put("secret", "450823f32e6c0864a51a2524bb5ddb3d");
	    String content = HttpClientUtil.doPost(accesstoken, m);
	    // 获取token
	    com.alibaba.fastjson.JSONObject jsonObj = JSON.parseObject(content);
	    String accessToken = (String) jsonObj.get("access_token");
		try {
			
	    	JSONObject json1 = new JSONObject();
	    	JSONObject json2 = new JSONObject();
	    	
	    	json2.put("value", "有新订单,请查看");
	    	json1.put("first", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", name);
	    	json1.put("keyword1", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", phone);
	    	json1.put("keyword2", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", address);
	    	json1.put("keyword3", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", addtime);
	    	json1.put("keyword4", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", trial);
	    	json1.put("keyword5", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", "订单查看");
	    	json1.put("remark", json2);
	    	
			String path = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+accessToken;
			obj.put("touser", openid);
			mp_template_msg.put("appid","wx2da6d0a84363b015");
			mp_template_msg.put("template_id", "dNODmY6sbVpaUkpZxrgk2SqN_E2cO4nRNp0yaZ601uY");
			mp_template_msg.put("url","https://www.baidu.com");
			miniprogram.put("appid", "wxf020b9146ae3ec37");
			//miniprogram.put("pagepath", "pages/jiedanxiangqing/jiedanxiangqing");
			miniprogram.put("pagepath", "pages/bangwozhaoshi-detail/bangwozhaoshi-detail?isShowBtn=true&id="+id+"&ismine=0&stuid="+stuid +"");			
			mp_template_msg.put("miniprogram", miniprogram);
			mp_template_msg.put("data", json1);
			obj.put("mp_template_msg", mp_template_msg);
			System.out.println(obj);
			result = WXTmplMsgUtils.Post(path, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
    
    
    
    
    public JSONObject sendMessage(String openid,String project,String addtime,String address,String grade,String id,String stuid){
		JSONObject result = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONObject mp_template_msg = new JSONObject();
		JSONObject miniprogram = new JSONObject();
		// 获取token
	    String accesstoken = "https://api.weixin.qq.com/cgi-bin/token";
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("grant_type", "client_credential");
	    m.put("appid", "wxf020b9146ae3ec37");
	    m.put("secret", "450823f32e6c0864a51a2524bb5ddb3d");
	    String content = HttpClientUtil.doPost(accesstoken, m);
	    // 获取token
	    com.alibaba.fastjson.JSONObject jsonObj = JSON.parseObject(content);
	    String accessToken = (String) jsonObj.get("access_token");
		try {
			
	    	JSONObject json1 = new JSONObject();
	    	JSONObject json2 = new JSONObject();
	    	
	    	json2.put("value", "有新派单,请查看");
	    	json1.put("first", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", project);
	    	json1.put("keyword1", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", addtime);
	    	json1.put("keyword2", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", address);
	    	json1.put("keyword3", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", grade);
	    	json1.put("keyword4", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", "无");
	    	json1.put("keyword5", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", "订单查看");
	    	json1.put("remark", json2);
	    	
			String path = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+accessToken;
			obj.put("touser", openid);
			mp_template_msg.put("appid","wx2da6d0a84363b015");
			mp_template_msg.put("template_id", "2y51Fci1pAeBsN8KvvLAOw6k2_4Zn6K3c_f5PAT1d-c");
			mp_template_msg.put("url","https://www.baidu.com");
			miniprogram.put("appid", "wxf020b9146ae3ec37");
			//miniprogram.put("pagepath", "pages/jiedanxiangqing/jiedanxiangqing");
			miniprogram.put("pagepath", "pages/bangwozhaoshi-detail/bangwozhaoshi-detail?isShowBtn=true&id="+id+"&ismine=0&stuid="+stuid +"");			
			mp_template_msg.put("miniprogram", miniprogram);
			mp_template_msg.put("data", json1);
			obj.put("mp_template_msg", mp_template_msg);
			System.out.println(obj);
			result = WXTmplMsgUtils.Post(path, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
    
    
    
    
    
    
    
    
     
//    支付成功，管理员收到消息
    public JSONObject sendPay(String openid,String money,String message,String ordernum){
    	JSONObject result = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONObject mp_template_msg = new JSONObject();
		JSONObject miniprogram = new JSONObject();
		// 获取token
	    String accesstoken = "https://api.weixin.qq.com/cgi-bin/token";
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("grant_type", "client_credential");
	    m.put("appid", "wxf020b9146ae3ec37");
	    m.put("secret", "450823f32e6c0864a51a2524bb5ddb3d");
	    String content = HttpClientUtil.doPost(accesstoken, m);
	    // 获取token
	    com.alibaba.fastjson.JSONObject jsonObj = JSON.parseObject(content);
	    String accessToken = (String) jsonObj.get("access_token");
		try {
			
	    	JSONObject json1 = new JSONObject();
	    	
	    	JSONObject json2 = new JSONObject();
	    	json2.put("value", "收到一笔客户支付,请及时查看");
	    	json1.put("first", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", money);
	    	json1.put("keyword1", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", message);
	    	json1.put("keyword2", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", ordernum);
	    	json1.put("keyword3", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", "订单查看");
	    	json1.put("remark", json2);
	    	
			String path = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+accessToken;
			obj.put("touser", openid);
			mp_template_msg.put("appid","wx2da6d0a84363b015");
			mp_template_msg.put("template_id", "uRJabPdJfVF1yIs07ROuiAXLEYerlpw_bbDLd0vzzGQ");
			mp_template_msg.put("url","https://www.baidu.com");
			miniprogram.put("appid", "wxf020b9146ae3ec37");
			miniprogram.put("pagepath", "");
			mp_template_msg.put("miniprogram", miniprogram);
			mp_template_msg.put("data", json1);
			obj.put("mp_template_msg", mp_template_msg);
			System.out.println(obj);
			result = WXTmplMsgUtils.Post(path, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
       
    
    //打卡通知
    public JSONObject sendPunch(String openid,String project,String addtime,String tname){
    	JSONObject result = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONObject mp_template_msg = new JSONObject();
		JSONObject miniprogram = new JSONObject();
		// 获取token
	    String accesstoken = "https://api.weixin.qq.com/cgi-bin/token";
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("grant_type", "client_credential");
	    m.put("appid", "wxf020b9146ae3ec37");
	    m.put("secret", "450823f32e6c0864a51a2524bb5ddb3d");
	    String content = HttpClientUtil.doPost(accesstoken, m);
	    // 获取token
	    com.alibaba.fastjson.JSONObject jsonObj = JSON.parseObject(content);
	    String accessToken = (String) jsonObj.get("access_token");
		try {
			
	    	JSONObject json1 = new JSONObject();
	    	
	    	JSONObject json2 = new JSONObject();
	    	json2.put("value", "教师已打卡,请查看");
	    	json1.put("first", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", project);
	    	json1.put("keyword1", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", addtime);
	    	json1.put("keyword2", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", tname);
	    	json1.put("keyword3", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", "前往查看");
	    	json1.put("remark", json2);
	    	
			String path = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+accessToken;
			obj.put("touser", openid);
			mp_template_msg.put("appid","wx2da6d0a84363b015");
			mp_template_msg.put("template_id", "MxP66FZYPU8t7vx2-hgT9aYtgX0cWAX68uVkVV_Kuj4");
			mp_template_msg.put("url","https://www.baidu.com");
			miniprogram.put("appid", "wxf020b9146ae3ec37");
			miniprogram.put("pagepath", "");
			mp_template_msg.put("miniprogram", miniprogram);
			mp_template_msg.put("data", json1);
			obj.put("mp_template_msg", mp_template_msg);
			System.out.println(obj);
			result = WXTmplMsgUtils.Post(path, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
    
    
    
    //老师应聘生源单，家长收到通知
    public JSONObject yingpin(String frist,String openid,String project,String addtime,String address,String status){
    	JSONObject result = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONObject mp_template_msg = new JSONObject();
		JSONObject miniprogram = new JSONObject();
		// 获取token
	    String accesstoken = "https://api.weixin.qq.com/cgi-bin/token";
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("grant_type", "client_credential");
	    m.put("appid", "wxf020b9146ae3ec37");
	    m.put("secret", "450823f32e6c0864a51a2524bb5ddb3d");
	    String content = HttpClientUtil.doPost(accesstoken, m);
	    // 获取token
	    com.alibaba.fastjson.JSONObject jsonObj = JSON.parseObject(content);
	    String accessToken = (String) jsonObj.get("access_token");
		try {
			
	    	JSONObject json1 = new JSONObject();
	    	
	    	JSONObject json2 = new JSONObject();
	    	json2.put("value", frist);
	    	json1.put("first", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", project);
	    	json1.put("keyword1", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", addtime);
	    	json1.put("keyword2", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", address);
	    	json1.put("keyword3", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", status);
	    	json1.put("keyword4", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", "前往查看");
	    	json1.put("remark", json2);
	    	
			String path = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+accessToken;
			obj.put("touser", openid);
			mp_template_msg.put("appid","wx2da6d0a84363b015");
			mp_template_msg.put("template_id", "1liwSBt3UrUd5mLdJEzX-Bnc9zX4O_t4AZi9JkBwWeI");
			mp_template_msg.put("url","https://www.baidu.com");
			miniprogram.put("appid", "wxf020b9146ae3ec37");
			miniprogram.put("pagepath", "pages/jiedanxiangqing/jiedanxiangqing");
			mp_template_msg.put("miniprogram", miniprogram);
			mp_template_msg.put("data", json1);
			obj.put("mp_template_msg", mp_template_msg);
			System.out.println(obj);
			result = WXTmplMsgUtils.Post(path, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
    
    //家长指定时间下单，老师收到信息
    public JSONObject paypush(String frist,String openid,String project,String addtime,String address,String status,int id,int stuid){
    	JSONObject result = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONObject mp_template_msg = new JSONObject();
		JSONObject miniprogram = new JSONObject();
		// 获取token
	    String accesstoken = "https://api.weixin.qq.com/cgi-bin/token";
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("grant_type", "client_credential");
	    m.put("appid", "wxf020b9146ae3ec37");
	    m.put("secret", "450823f32e6c0864a51a2524bb5ddb3d");
	    String content = HttpClientUtil.doPost(accesstoken, m);
	    // 获取token
	    com.alibaba.fastjson.JSONObject jsonObj = JSON.parseObject(content);
	    String accessToken = (String) jsonObj.get("access_token");
		try {
			
	    	JSONObject json1 = new JSONObject();
	    	
	    	JSONObject json2 = new JSONObject();
	    	json2.put("value", frist);
	    	json1.put("first", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", project);
	    	json1.put("keyword1", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", addtime);
	    	json1.put("keyword2", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", address);
	    	json1.put("keyword3", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", status);
	    	json1.put("keyword4", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", "前往查看");
	    	json1.put("remark", json2);
	    	
			String path = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+accessToken;
			obj.put("touser", openid);
			mp_template_msg.put("appid","wx2da6d0a84363b015");
			mp_template_msg.put("template_id", "1liwSBt3UrUd5mLdJEzX-Bnc9zX4O_t4AZi9JkBwWeI");
			mp_template_msg.put("url","https://www.baidu.com");
			miniprogram.put("appid", "wxf020b9146ae3ec37");
			String pagepath = "../bangwozhaoshi-detail/bangwozhaoshi-detail?isShowBtn=true&id="+id+"&ismine=0&stuid="+stuid;
			miniprogram.put("pagepath",pagepath);
			mp_template_msg.put("miniprogram", miniprogram);
			mp_template_msg.put("data", json1);
			obj.put("mp_template_msg", mp_template_msg);
			System.out.println(obj);
			result = WXTmplMsgUtils.Post(path, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
    
    
    
    //客户填写“帮我选师”后，家长同意其中一位老师上课，管理要能收到提醒
    public JSONObject parentPass(String frist,String openid,String project,String addtime,String address,String status){
    	JSONObject result = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONObject mp_template_msg = new JSONObject();
		JSONObject miniprogram = new JSONObject();
		// 获取token
	    String accesstoken = "https://api.weixin.qq.com/cgi-bin/token";
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("grant_type", "client_credential");
	    m.put("appid", "wxf020b9146ae3ec37");
	    m.put("secret", "450823f32e6c0864a51a2524bb5ddb3d");
	    String content = HttpClientUtil.doPost(accesstoken, m);
	    // 获取token
	    com.alibaba.fastjson.JSONObject jsonObj = JSON.parseObject(content);
	    String accessToken = (String) jsonObj.get("access_token");
		try {
			
	    	JSONObject json1 = new JSONObject();
	    	
	    	JSONObject json2 = new JSONObject();
	    	json2.put("value", frist);
	    	json1.put("first", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", project);
	    	json1.put("keyword1", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", addtime);
	    	json1.put("keyword2", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", address);
	    	json1.put("keyword3", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", status);
	    	json1.put("keyword4", json2);
	    	
	    	json2 = new JSONObject();
	    	json2.put("value", "前往查看");
	    	json1.put("remark", json2);
	    	
			String path = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+accessToken;
			obj.put("touser", openid);
			mp_template_msg.put("appid","wx2da6d0a84363b015");
			mp_template_msg.put("template_id", "1liwSBt3UrUd5mLdJEzX-Bnc9zX4O_t4AZi9JkBwWeI");
			mp_template_msg.put("url","https://www.baidu.com");
			miniprogram.put("appid", "wxf020b9146ae3ec37");
			miniprogram.put("pagepath", "");
			mp_template_msg.put("miniprogram", miniprogram);
			mp_template_msg.put("data", json1);
			obj.put("mp_template_msg", mp_template_msg);
			System.out.println(obj);
			result = WXTmplMsgUtils.Post(path, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
    
    
    
    
    
    
    
    
    
    
    

//    //新订单通知
//    public boolean newOrder(String openid,String value1,String value2) throws Exception {
//    	System.out.println(value1);
//    	System.out.println(value2);
//    	
//    	JSONObject jo = getAccessToken();
//    	if(jo.get("access_token") == null || jo.get("access_token").toString()=="") {
//    		System.err.println("======================获取token失败");
//    		return false;
//    	}
//    	JSONObject json = new JSONObject();
//    	
//    	JSONObject json1 = new JSONObject();
//    	JSONObject json2 = new JSONObject();
//    	
//    	//订单内容
//    	json2.put("value", value1);
//    	json1.put("thing1", json2);
//    	
//    	//订单时间
//    	json2 = new JSONObject();
//    	json2.put("value", value2);
//    	json1.put("time2", json2);
//    	
//    	
//    	json.put("touser", openid);
//    	json.put("template_id", "5zLyBN63sIRF8CtFa5fgMBidY0HEICby5qjTrLclYhw");
//    	json.put("page", "pages/jiedanxiangqing/jiedanxiangqing");
//    	json.put("data", json1);
//    	
//    	System.out.println(json.toString());
//    	boolean pushResult = setPush(json.toString(),jo.get("access_token").toString());
//    	System.out.println(pushResult);
//    	return pushResult;
//    }
    
    
    
    
    //推送工具类
//    public static boolean setPush(String params, String accessToken) {
//        boolean flag = false;
//        String url = PUSH_URL + accessToken;
//        OutputStream outputStream = null;
//        InputStreamReader inputStreamReader = null;
//        InputStream inputStream = null;
//        BufferedReader bufferedReader = null;
//        HttpsURLConnection connection = null;
//        try {
//            // 创建URL对象
//            URL realUrl = new URL(url);
//            // 打开连接 获取连接对象
//            connection = (HttpsURLConnection) realUrl.openConnection();
//            // 设置请求编码
//            connection.addRequestProperty("encoding", "UTF-8");
//            // 设置允许输入
//            connection.setDoInput(true);
//            // 设置允许输出
//            connection.setDoOutput(true);
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//            // 当outputStr不为null时向输出流写数据
//            if (null != params) {
//                outputStream = connection.getOutputStream();
//                // 注意编码格式
//                outputStream.write(params.getBytes("UTF-8"));
//                outputStream.close();
//            }
//            // 从输入流读取返回内容
//            inputStream = connection.getInputStream();
//            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
//            bufferedReader = new BufferedReader(inputStreamReader);
//            String str = null;
//            StringBuffer buffer = new StringBuffer();
//            while ((str = bufferedReader.readLine()) != null) {
//                buffer.append(str);
//            }
//            JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
//            int errorCode = jsonObject.getInteger("errcode");
//            String errorMessage = jsonObject.getString("errmsg");
//            if (errorCode == 0) {
//                flag = true;
//            } else {
//               System.out.println("模板消息发送失败:" + errorCode + "," + errorMessage);
//                flag = false;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 依次关闭打开的输入流
//            try {
//                connection.disconnect();
//                bufferedReader.close();
//                inputStreamReader.close();
//                inputStream.close();
//                // 依次关闭打开的输出流
//                outputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return flag;
//    }
//    
//    
//    
//    
//    //获取token工具类
//    public static JSONObject getAccessToken() {
//        String url = ACCESS_TOKEN_URL + "&appid=" + APP_ID + "&secret=" + SECRET;
//        System.out.println(url);
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String line;
//        StringBuffer sb = new StringBuffer();
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//
//            // 设置通用的请求属性 设置请求格式
//            //设置返回类型
//            conn.setRequestProperty("contentType", "text/plain");
//            //设置请求类型
//            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//            //设置超时时间
//            conn.setConnectTimeout(1000);
//            conn.setReadTimeout(1000);
//            conn.setDoOutput(true);
//            conn.connect();
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应    设置接收格式
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            while ((line = in.readLine()) != null) {
//                sb.append(line);
//            }
//            // 将获得的String对象转为JSON格式
//            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
//            return jsonObject;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //使用finally块来关闭输出流、输入流
//        finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return null;
//    }
    
}
