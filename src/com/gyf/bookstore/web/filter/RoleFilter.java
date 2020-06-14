package com.gyf.bookstore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.domain.User;
//只拦截admin路径下的请求
//@WebFilter("/admin/*")
public class RoleFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		User user = (User) request.getSession().getAttribute("user");
		
		if(user != null){
			if(!"管理员".equals(user.getRole())){
				response.getWriter().write("权限不足，没有管理员权限,使用管理员帐号登录");
				response.setHeader("Refresh", "3;url="+ request.getContextPath()+"/index.jsp");
				return;
			}else{
				//放行
				chain.doFilter(request, response);
				return;
			}
		
		}
		
		//进入登录界面
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
