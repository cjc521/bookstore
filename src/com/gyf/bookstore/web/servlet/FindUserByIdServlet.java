package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.domain.User;
import com.gyf.bookstore.exception.UserException;
import com.gyf.bookstore.service.UserService;

@WebServlet("/findUserById")
public class FindUserByIdServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		UserService us = new UserService();
		try {
			User user = us.findUserById(id);
			request.setAttribute("u", user);
			request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
		} catch (UserException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}
}
