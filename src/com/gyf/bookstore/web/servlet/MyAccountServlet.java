package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.domain.User;

@WebServlet("/myacount")
public class MyAccountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null){
			//未登录进行登录界面
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}else{
			//进入我的帐号页面
			request.getRequestDispatcher("/myAccount.jsp").forward(request, response);
		}
		
	}
}
