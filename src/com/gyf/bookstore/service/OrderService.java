package com.gyf.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.gyf.bookstore.dao.OrderDao;
import com.gyf.bookstore.dao.OrderItemDao;
import com.gyf.bookstore.dao.ProductDao;
import com.gyf.bookstore.domain.Order;
import com.gyf.bookstore.domain.OrderItem;
import com.gyf.bookstore.utils.ManagerThreadLocal;

public class OrderService {

	OrderDao orderDao = new OrderDao();
	OrderItemDao orderItemDao = new OrderItemDao();
	ProductDao productDao = new ProductDao();
	/**
	 * 保存定单
	 * @param order
	 */
	public void addOrder(Order order){
		//1.开启事务
		ManagerThreadLocal.beginTransaction();
		try {
			//2.保存定单表
			orderDao.saveOrder(order);
			
			//3.保存订单详情表
			orderItemDao.saveOrderItems(order.getOrderItems());
			
			//4.有时间也使用批处理
			for(OrderItem oi : order.getOrderItems()){
				//orderItemDao.saveOrderItem(oi);
				//更改产品可卖数量
//				productDao.updateProductNum(oi.getP().getId(),oi.getBuynum());
			}
			//4.结束事务
			ManagerThreadLocal.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			//5.回滚
			ManagerThreadLocal.rollback();
		}
	}
	
	/**
	 * 通过用户ID查找定单
	 * @param userid
	 * @return
	 */
	public List<Order> findOrdersByUserId(String userid){
		try {
			return orderDao.findOrdersByUserId(userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过定单号返回定单及详细信息
	 * @param orderId
	 * @return
	 */
	public Order findOrderByOrderId(String orderId){
		try {
			return orderDao.findOrderByOrderId(orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
