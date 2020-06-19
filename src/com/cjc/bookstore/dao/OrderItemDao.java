package com.cjc.bookstore.dao;

import java.sql.SQLException;
import java.util.List;

import com.cjc.bookstore.domain.OrderItem;
import com.cjc.bookstore.utils.C3P0Utils;
import com.cjc.bookstore.utils.ManagerThreadLocal;
import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.BeanListHandler;

public class OrderItemDao {
	//保存订单详情
	public void saveOrderItem(OrderItem oi) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into orderitem values (?,?,?)";
		
		QueryRunner qr = new QueryRunner();
		
		qr.update(ManagerThreadLocal.getConnection(), sql, oi.getOrder().getId(),oi.getP().getId(),oi.getBuynum());
		
	}
	  //批量保存订单详情
	public void saveOrderItems(List<OrderItem> items) throws SQLException{
		//1.封装参数
		Object[][] params = new Object[items.size()][];
		for(int i=0;i<items.size();i++){
			OrderItem oi = items.get(i);
			params[i] = new Object[]{oi.getOrder().getId(),oi.getP().getId(),oi.getBuynum()};
		}
		
		//2.批处理
		String sql = "insert into orderitem values (?,?,?)";
		
		QueryRunner qr = new QueryRunner();
		
		qr.batch(ManagerThreadLocal.getConnection(), sql,params );
	}
	//订单号查找订单详情
	public List<OrderItem> queryOrderItem(String orderId) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql="select products.* ,orderitem.buynum from products join orderitem " +
				"on orderitem.order_id=? and " +
				" products.id=orderitem.product_id ";
		return qr.query(sql,new BeanListHandler<OrderItem>(OrderItem.class),orderId);
	}
}
