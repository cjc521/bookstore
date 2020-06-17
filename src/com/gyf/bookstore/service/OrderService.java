package com.gyf.bookstore.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gyf.bookstore.dao.OrderDao;
import com.gyf.bookstore.dao.OrderItemDao;
import com.gyf.bookstore.dao.ProductDao;
import com.gyf.bookstore.dao.UserDao;
import com.gyf.bookstore.domain.Order;
import com.gyf.bookstore.domain.OrderItem;
import com.gyf.bookstore.domain.Product;
import com.gyf.bookstore.utils.ManagerThreadLocal;
import sun.plugin.javascript.navig.Link;

public class OrderService {
	OrderDao orderDao = new OrderDao();
	OrderItemDao orderItemDao = new OrderItemDao();
	ProductDao productDao = new ProductDao();

	 // 保存定单
	public void addOrder(Order order){
		//1.开启事务
		ManagerThreadLocal.beginTransaction();
		try {
			//2.保存定单表
			orderDao.saveOrder(order);
			//3.保存订单详情表
			orderItemDao.saveOrderItems(order.getOrderItems());
			//4.结束事务
			ManagerThreadLocal.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			//5.回滚
			ManagerThreadLocal.rollback();
		}
	}
	//修改订单状态
	public void updateState(String id) throws SQLException {
		orderDao.updateState(id);
	}
	//
	public List<Order> findOrdersByUserId(int userId){
		try {
			return orderDao.findOrdersByUserId(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//订单号查找订单
	public Order findOrderByOrderId(String orderId){
		Order order =null;
		try {
			order = orderDao.findOrderByOrderId(orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	//删除订单
	public int deleteOrderById(String id){
		int count=0;
		try {
			count = orderDao.deleteOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//订单号查找图书
	public List<OrderItem> findOrderItemByOrderId(String orderId){
		List<OrderItem> orderItems=null;
		try {
		    orderItems = orderItemDao.queryOrderItem(orderId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItems;
	}
}
