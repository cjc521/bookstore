package com.gyf.bookstore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.gyf.bookstore.domain.OrderItem;
import com.gyf.bookstore.utils.ManagerThreadLocal;
import com.mchange.v2.resourcepool.ResourcePool.Manager;

public class OrderItemDao {

	public void saveOrderItem(OrderItem oi) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into orderitem values (?,?,?)";
		
		QueryRunner qr = new QueryRunner();
		
		qr.update(ManagerThreadLocal.getConnection(), sql, oi.getOrder().getId(),oi.getP().getId(),oi.getBuynum());
		
	}

	
	/**
	 * 批量保存订单详情
	 * @param items
	 * @throws SQLException
	 */
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
}
