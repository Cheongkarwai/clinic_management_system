package com.cheong.clinic.config;

import org.springframework.web.servlet.HandlerInterceptor;


//Testing for custom interceptor
public class CustomInterceptor implements HandlerInterceptor{

	/*@Override
	public boolean preHandle(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object handler)throws Exception{
		
		HttpSession httpSession = httpServletRequest.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		
		//PrintWriter writer = httpServletResponse.getWriter();
		
		if(httpServletRequest.getRequestURI().startsWith("/admin")) {
			if(user == null) {
				return false;
			}
			else {
				if(!user.getRole().equals("ROLE_ADMIN")) {
					httpServletResponse.getWriter().write("ACCESS IS FORBIDDEN");
					return false;
				}
			}
			
		}
		
		return true;
	}*/
}
