package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.gyf.bookstore.domain.User;
import com.gyf.bookstore.service.UserService;

@WebServlet("/modifyUserInfo")
public class ModifyUserInfoServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取请求参数
		User user = new User();
		try {
			//map转模型
			BeanUtils.populate(user, request.getParameterMap());
			
			//调用业务
			UserService us = new UserService();
			us.modifyUser(user);
			
			//session失效
			request.getSession().invalidate();
			
			response.sendRedirect(request.getContextPath() + "/modifyUserInfoSuccess.jsp");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
		} 
	}
}
