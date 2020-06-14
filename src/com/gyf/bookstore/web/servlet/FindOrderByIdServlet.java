package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.domain.Order;
import com.gyf.bookstore.domain.User;
import com.gyf.bookstore.service.OrderService;

@WebServlet("/findOrderById")
public class FindOrderByIdServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			response.getWriter().write("非法访问...");
			return;
		}
		
		//获取定单
		OrderService os = new OrderService();
		List<Order> list = os.findOrdersByUserId(user.getId()+"");
		request.setAttribute("orders",list);
		request.setAttribute("count",list.size());
		//转发
		request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
		
	}
}
