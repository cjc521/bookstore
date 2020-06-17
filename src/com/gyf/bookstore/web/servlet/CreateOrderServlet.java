package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.gyf.bookstore.domain.Order;
import com.gyf.bookstore.domain.OrderItem;
import com.gyf.bookstore.domain.Product;
import com.gyf.bookstore.domain.User;
import com.gyf.bookstore.service.OrderService;

@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String receiverAddress = request.getParameter("receiverAddress");
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		Order order = new Order();
		
		//获取当前登录用户的信息
		User user = (User) request.getSession().getAttribute("user");
		try {
			//1.把请求通过封闭到Order中
			BeanUtils.populate(order, request.getParameterMap());
			//2.设置用户
			order.setUser(user);
			//3.设置ID
			order.setId(UUID.randomUUID().toString());
			System.out.println(UUID.randomUUID());
			//4.设置定单详情
			double totalPrice=0;
			Map<Product,Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			for(Entry<Product,Integer> entry : cart.entrySet()){
				OrderItem oi = new OrderItem();
				oi.setBuynum(entry.getValue());
				oi.setP(entry.getKey());
				oi.setOrder(order);
				orderItems.add(oi);
				totalPrice+= entry.getKey().getPrice() * entry.getValue();
			}
			order.setOrderItems(orderItems);
			//5.设置定单时间和金额
			order.setOrdertime(new Date());
			order.setMoney(totalPrice);
			//6.订单保存
			OrderService os = new OrderService();
			os.addOrder(order);
			//7.进入支付页面
			request.getSession().setAttribute("order",order);
			response.sendRedirect(request.getContextPath()+"/pay.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
