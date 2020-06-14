package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.domain.User;

@WebServlet("/settleAccounts")
public class SettleAccountsServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/order.jsp").forward(request, response);
		}
	}
}
