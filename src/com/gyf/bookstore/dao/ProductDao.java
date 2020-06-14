package com.gyf.bookstore.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gyf.bookstore.domain.Product;
import com.gyf.bookstore.utils.C3P0Utils;
import com.gyf.bookstore.utils.ManagerThreadLocal;
import com.mchange.v2.resourcepool.ResourcePool.Manager;

public class ProductDao{
//	private static int productId=0;
	//查询类别的总记录数
	public long count(String category) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(*) from products where 1=1";
		
		long count = 0;
		if(category != null && !"".equals(category)){
			sql+= " and  category = ?";
			count = (long) qr.query(sql, new ScalarHandler(1), category);
		}else{
			count = (long) qr.query(sql, new ScalarHandler(1));
		}
		
		return count;
	}
	
	//分页查找数据
	public  List<Product> findBooks(int currentPage,int pageCount,String category) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		//定义参数
		List<Object> prmts = new ArrayList<>();
		
		//查询的sql语句
		String sql = "select * from products where 1=1";

		//条件
		if(category!=null && !"".equals(category)){
			sql += " and category = ?";
			prmts.add(category);
		}
		
		//分页
		sql += " limit ?,?";
		int start = (currentPage - 1) * pageCount;
		prmts.add(start);
		prmts.add(pageCount);
		List<Product> products = qr.query(sql, new BeanListHandler<Product>(Product.class), prmts.toArray());

		return products;
	}
	
	/**
	 * 根据ID查找书产品
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Product findBookById(String id) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from products where id = ?";
		return qr.query(sql, new BeanHandler<Product>(Product.class),id);
	}

	/**
	 * 更新产品的可卖数量
	 * @param id
	 * @param buynum
	 * @throws SQLException 
	 */
	public void updateProductNum(String id, int buynum) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "update products set pnum = pnum - ? where id = ?";
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), sql,buynum,id);
	}
	public int addProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

		String sql = "insert into  products(name,price,category,pnum,imgurl,description)" +
				" values(?,?,?,?,?,?) ";

		int count = qr.update(sql,product.getName(), product.getPrice(), product.getCategory(), product.getPnum(),
				product.getImgurl(), product.getDescription());
		return count;
	}
	public int queryLastId(){
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql="select id from products where order by id desc limit 1";
		int id=0;
		try {
			id= qr.query(sql, new BeanHandler<Integer>(Integer.class));
		} catch (SQLException e) {
		  e.printStackTrace();
		}
		return id;
	}
}
