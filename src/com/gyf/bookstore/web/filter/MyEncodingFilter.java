package com.gyf.bookstore.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
@WebFilter("/*")
public class MyEncodingFilter implements Filter{
	//有无Override没影响
	public void destroy() {}
	public void init(FilterConfig config) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		//1.类型转换
//		HttpServletRequest hsr = (HttpServletRequest) request;
		
		//2.更改request

		// 处理响应乱码
		response.setContentType("text/html;charset=utf-8");
		
		//3.放行
		chain.doFilter(request, response);
	}
}

