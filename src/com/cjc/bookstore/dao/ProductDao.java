package com.cjc.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cjc.bookstore.domain.Product;
import com.cjc.bookstore.utils.C3P0Utils;
import com.cjc.bookstore.utils.ManagerThreadLocal;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class ProductDao{
	//查询类别的总记录数
	public long count(String category) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(id) from products where 1=1 ";
		List<Object> prmts = new ArrayList<>();
		//条件
		if(category!=null && !"".equals(category)){
			sql += " and category = ?";
			prmts.add(category);
		}
		long  count = (long) qr.query(sql, new ScalarHandler(1),prmts.toArray());
		return count;
	}
	//模糊查询name的记录数
	public long countOfName(String name) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql="select count(id) from products where name like ? ";
		long count =(long) qr.query(sql,new ScalarHandler(1),"%"+name+"%");
		return count;
	}
	//分页查找数据
	public  List<Product> findBooks(int currentPage, int pageCount, String category) throws SQLException{
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
	//分页模糊查询
	public  List<Product> findBooksByName(int currentPage,int pageCount,String name) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		//查询的sql语句
		String sql = "select * from products where name like ? limit ?,?";
		int start = (currentPage - 1) * pageCount;
		List<Product> products = qr.query(sql, new BeanListHandler<Product>(Product.class),
				"%"+name+"%",start,pageCount);
		return products;
	}
	 //查找书籍
	public Product findBookById(int id) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from products where id = ?";
		return qr.query(sql, new BeanHandler<Product>(Product.class),id);
	}
	//添加图书
	public int addProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

		String sql = "insert into  products(name,price,category,pnum,imgurl,description)" +
				" values(?,?,?,?,?,?) ";

		int count = qr.update(sql,product.getName(), product.getPrice(), product.getCategory(), product.getPnum(),
				product.getImgurl(), product.getDescription());
		return count;
	}
	//修改图书库存
	public int updateProductNum(int id, int buynum) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update products set pnum = pnum - ? where id = ?";
		QueryRunner qr = new QueryRunner();
		return qr.update(ManagerThreadLocal.getConnection(), sql,buynum,id);
	}
	//多条件条件查询图书
	public List<Product> searchIf(String id, String category, String name, String minprice, String maxprice,int start,int pageCount) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		//没有条件，查询所有
		String sql = "select * from products where 1=1";
		List<Object> list = new ArrayList<>();
		//如果选择了书的编号
		if (id != null && id != "") {
			sql += " and id like ?";
			list.add(Integer.parseInt(id));
		}
		//如果选择了书的种类
		if (category != null && category != "") {
			sql += " and category=?";
			list.add(category);
		}
		//如果选择了书的名称
		if (name != null && name != "") {
			sql += " and name like ?";
			list.add("%" + name + "%");
		}
		//如果选择了书的价格区间
		if ((minprice != null && !("").equals(minprice)) && (maxprice != null && !"".equals(maxprice) ) ){
			sql += " and price between ? and ? ";
			list.add(minprice);
			list.add(maxprice);
		}
		sql+="  limit  ?,?";
		list.add(start);
		list.add(pageCount);
		List<Product> products = qr.query(sql, new BeanListHandler<Product>(Product.class), list.toArray());
		return products;
	}
    //多条件查询图书总记录数
	public long countOfManyConddition(String id, String category, String name, String minprice, String maxprice) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		Long count;
		//没有条件，查询所有
		String sql = "select count(id) from products where 1=1";
		List<Object> list = new ArrayList<>();
		//如果选择了书的编号
		if (id != null && id != "") {
			sql += " and id like ?";
			list.add(Integer.parseInt(id));
		}
		//如果选择了书的种类
		if (category != null && category != "") {
			sql += " and category=?";
			list.add(category);
		}
		//如果选择了书的名称
		if (name != null && name != "") {
			sql += " and name like ?";
			list.add("%"+ name + "%");
		}
		//如果选择了书的价格区间
		if ((minprice != null && !("").equals(minprice)) && (maxprice != null && !"".equals(maxprice) ) ){
			sql += " and price between ? and ? ";
			list.add(minprice);
			list.add(maxprice);
		}
		count = (long) qr.query(sql, new ScalarHandler(1), list.toArray());
		return count;
	}
    //id删除图书
	public int deleteProductById(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "delete  from products where 1=1";
		List<Integer> list = new ArrayList<Integer>();
		if(id !=null && !"".equals(id)){
			sql+=" and id =?";
			list.add(Integer.parseInt(id));
		}
		return  qr.update(sql,list.toArray());
	}
	//修改图书
	public int updateProduct(int id, String name, double price, String category, int pnum, String imgurl, String description) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		//没有条件，查询所有
		String sql = "update  products set id=id";
		List<Object> list = new ArrayList<>();
		//如果选择了书的种类
		if (category != null && !"".equals(category) ){
			sql += ",category =?";
			list.add(category);
		}
		//如果选择了书的名称
		if (name != null && !"".equals(name)) {
			sql += ",name =?";
			list.add(name);
		}
		if (imgurl != null && !"".equals(imgurl)) {
			sql += ",imgurl =?";
			list.add(imgurl);
		}
		if (String.valueOf(pnum) != null && !"".equals(String.valueOf(pnum))) {
			sql += ",pnum =?";
			list.add(pnum);
		}
		if ( String.valueOf(price) != null && !"".equals(String.valueOf(price) )) {
			sql += ",price =?";
			list.add(price);
		}
		if (description != null && !"".equals(description)) {
			sql += ",description =?";
			list.add(description);
		}
		sql+="  where id=?";
		list.add(id);
		return qr.update(sql,list.toArray());
	}

}







