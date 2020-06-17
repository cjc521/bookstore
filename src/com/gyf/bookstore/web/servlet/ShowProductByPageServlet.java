package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.dao.ProductDao;
import com.gyf.bookstore.domain.PageResult;
import com.gyf.bookstore.domain.Product;
import com.gyf.bookstore.service.ProductService;

@WebServlet("/showProductByPage")
public class ShowProductByPageServlet extends HttpServlet{

	ProductService productService = new ProductService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取参数
		 String category = request.getParameter("category");//分类
		 String page = request.getParameter("page");//显示页数
		 //2.判断
		 int currentPage = 1;
		 int pageCount = 5;//每页显示5条数据,这个内部定义，不让外面传参数
		 if(page != null){
			 currentPage = Integer.parseInt(page);
		 }
		 //3.调用service
		 PageResult<Product> pr = productService.findPageBooks(currentPage, pageCount, category);
		 
		 //4.跳转
		 request.setAttribute("pr", pr);
		 request.setAttribute("category", category);
		 request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}
}
