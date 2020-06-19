package com.cjc.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjc.bookstore.exception.UserException;
import com.cjc.bookstore.service.UserService;

@WebServlet("/active")
public class ActiveServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String activeCode = request.getParameter("activeCode");
		System.out.println("接收到的激活码:" + activeCode);
		
		UserService us = new UserService();
		try {
			us.activeUser(activeCode);
			response.getWriter().write("激活成功");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
}
