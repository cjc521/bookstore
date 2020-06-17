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

@WebServlet("/findOrderByUserId")
public class FindOrderByUserIdServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		//处理未登录用户
		if(id == 0){
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		}
		//获取定单
		OrderService os = new OrderService();
		List<Order> list = os.findOrdersByUserId(id);
		request.setAttribute("orders",list);
		request.setAttribute("count",list.size());
		//转发
		request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
