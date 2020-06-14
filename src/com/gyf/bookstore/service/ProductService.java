package com.gyf.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.gyf.bookstore.dao.ProductDao;
import com.gyf.bookstore.domain.PageResult;
import com.gyf.bookstore.domain.Product;

public class ProductService {

	ProductDao productDao = new ProductDao();
	
	public PageResult<Product> findPageBooks(int currentPage,int pageCount,String category){
		try {
			//1.创建PageResult
			PageResult<Product> pr = new PageResult<Product>();
			
			//2.获取总记录数
			long totalCount = productDao.count(category);
			
			//3.计算总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 / pageCount) ;
			
			//4.查询数据库
			List<Product> list = productDao.findBooks(currentPage, pageCount, category);
			
			//5.设置PageResult
			pr.setCurrentPage(currentPage);//当前页
			pr.setPageCount(pageCount);//每页显示记录数
			pr.setTotalCount(totalCount);//总记录数
			pr.setTotalPage(totalPage);//总页数
			pr.setList(list);
			
			return pr;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	
	}

	public Product findBookById(String id) {
		try {
			return productDao.findBookById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int addProduct(Product product) {
		int count=0;
		try {
			count = productDao.addProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
