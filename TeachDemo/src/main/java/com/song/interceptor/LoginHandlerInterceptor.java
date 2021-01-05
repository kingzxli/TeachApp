package com.song.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.song.pojo.Parent;
import com.song.service.ParentService;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor{
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String header = request.getHeader("session");
		System.out.println(header);
		if(header != null || header != "") {
			return true;
		}
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//		System.out.println(session.getId());
//		Object loginUser = session.getAttribute("parent");
//		System.out.println("login拦截前"+loginUser);
//		if(session.getId() != null) {
//			return true;
//		}
		reponseWrite401(response);
		return false;
	}
	
	private void reponseWrite401(HttpServletResponse response) throws Exception {
//        BaseResultVo resultVo = new BaseResultVo(401, "签名不正确或token失效");
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("status", 204);
        String res = JSONUtils.toJSONString(map);
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        //解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().print(res);
        response.getWriter().flush();
        response.getWriter().close();
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
