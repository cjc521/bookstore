package com.cjc.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cjc.bookstore.domain.Order;
import com.cjc.bookstore.domain.OrderItem;
import com.cjc.bookstore.domain.Product;
import com.cjc.bookstore.utils.C3P0Utils;
import com.cjc.bookstore.utils.ManagerThreadLocal;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class OrderDao {

	//保存订单
	public void saveOrder(Order order)throws SQLException {
		
		QueryRunner qr = new QueryRunner();
		
		String sql = "insert into orders values (?,?,?,?,?,?,?,?)";
		
		List<Object> prmts = new ArrayList<>();
		prmts.add(order.getId());
		prmts.add(order.getMoney());
		prmts.add(order.getReceiverAddress());
		prmts.add(order.getReceiverName());
		prmts.add(order.getReceiverPhone());
		prmts.add(order.getPaystate());
		prmts.add(order.getOrdertime());
		prmts.add(order.getUser().getId());
		qr.update(ManagerThreadLocal.getConnection(), sql,prmts.toArray());
		
	}
    //修改订单状态为已支付
	public void updateState(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql="update orders set payState=1 where id =?";
		qr.update(sql,id);
	}
 // 根据用户id查找定单
	public List<Order> findOrdersByUserId(int userId) throws SQLException {
		String sql = "select * from orders where user_id = ?";
		
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
		return qr.query(sql, new BeanListHandler<Order>(Order.class),userId);
	}

	  //根据定单ID查询定单和详情信息
	public Order findOrderByOrderId(String orderId)throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
		//1.查询定单表
		String sql = "select * from orders where id = ?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class),orderId);
		
		//2.查询定单下面的详情
		sql = "SELECT o.*,p.name,p.price FROM orderitem o, products p WHERE o.product_id = p.id AND o.order_id = ?";
		List<OrderItem> orderItems = qr.query(sql, new ResultSetHandler<List<OrderItem>>(){
			
			@Override
			public List<OrderItem> handle(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				List<OrderItem> items = new ArrayList<OrderItem>();
				while(rs.next()){
					//封装定单详情
					OrderItem item = new OrderItem();
					item.setBuynum(rs.getInt("buynum"));
					
					//封装产品【只获取商品名称和价格】
					Product p = new Product();
					p.setName(rs.getString("name"));
					p.setPrice((rs.getDouble("price")));
					
					//把产品放入详情
					item.setP(p);
					
					//把详情添加到集合
					items.add(item);
				}
				return items;
			}
			
		},orderId);
		
		order.setOrderItems(orderItems);
		
		return order;
	}
   //删除订单
	public int deleteOrderById(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete * from orders where id =?";
		return qr.update(sql,id);
	}
}
