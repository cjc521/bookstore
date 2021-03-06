package com.cjc.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.cjc.bookstore.dao.ProductDao;
import com.cjc.bookstore.domain.PageResult;
import com.cjc.bookstore.domain.Product;

public class ProductService {

	ProductDao productDao = new ProductDao();
	//分页分类查询图书
	public PageResult<Product> findPageBooks(int currentPage,int pageCount,String category){
		try {
			//1.创建PageResult
			PageResult<Product> pr = new PageResult<Product>();
			//2.获取总记录数
			long totalCount = productDao.count(category);
			//3.计算总页数
			int totalPage=(int) (totalCount%pageCount==0 ? totalCount/pageCount : (totalCount/pageCount)+1 ) ;
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
    //id查询图书
	public Product findBookById(int id) {
		try {
			return productDao.findBookById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//name模糊分页查询图书
	public PageResult<Product> findBookByName(int currentPage,int pageCount,String name){
		try {
			PageResult<Product> pr = new PageResult<>();
			long totalCount = productDao.countOfName(name);
			int totalPage=(int) (totalCount%pageCount==0 ? totalCount/pageCount : (totalCount/pageCount)+1 ) ;
			List<Product> products = productDao.findBooksByName(currentPage, pageCount, name);

			pr.setTotalCount(totalCount);
			pr.setTotalPage(totalPage);
			pr.setPageCount(pageCount);
			pr.setCurrentPage(currentPage);
			pr.setList(products);
			return pr;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//添加图书
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
	//修改图书库存
	public int updatePnum(int id,int pnum){
		int count=0;
		try {
			count = productDao.updateProductNum(id, pnum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//多条件查询图书
	public  PageResult<Product> findBookIf(String id, String category, String name, String minprice, String maxprice,int currentPage,int pageCount) {
		try {
			PageResult<Product> pr = new PageResult<>();
			long totalCount = productDao.countOfManyConddition(id, category, name, minprice, maxprice);
			int totalPage=(int) (totalCount%pageCount==0 ? totalCount/pageCount : (totalCount/pageCount)+1 ) ;
			int start=(currentPage-1)*pageCount;
			List<Product> products = productDao.searchIf(id, category, name, minprice, maxprice, start, pageCount);
            pr.setTotalPage(totalPage);
            pr.setTotalCount(totalCount);
            pr.setPageCount(pageCount);
            pr.setCurrentPage(currentPage);
            pr.setList(products);
            return pr;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
   //id删除图书
	public int deleteProduct(String id){
		try {
			return productDao.deleteProductById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	//id修改图书
	public int updateProduct(int id, String name, double price, String category, int pnum, String imgurl, String description){
		try {
			return productDao.updateProduct(id, name, price,category, pnum, imgurl, description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
