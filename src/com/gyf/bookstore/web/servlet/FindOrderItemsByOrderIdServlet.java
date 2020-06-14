package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.domain.Order;
import com.gyf.bookstore.service.OrderService;

@WebServlet("/findOrderItemsByOrderId")
public class FindOrderItemsByOrderIdServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取请求参数
		String orderid = request.getParameter("orderid");
		
		//查询定单
		OrderService os = new OrderService();
		Order order = os.findOrderByOrderId(orderid);
		
		//存数据到request
		request.setAttribute("order", order);
		request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);
		//System.out.println(orderid);
	}
}
