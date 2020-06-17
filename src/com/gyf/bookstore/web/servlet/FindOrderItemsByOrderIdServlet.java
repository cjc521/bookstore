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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取请求参数
		String orderId = req.getParameter("orderId");
		//查询定单
		OrderService os = new OrderService();
		Order order = os.findOrderByOrderId(orderId);
		if(order==null){
			resp.getWriter().write("该订单异常，请重试！！！");
		}
		//存数据到request
		req.setAttribute("order", order);
		req.getRequestDispatcher("/orderInfo.jsp").forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
