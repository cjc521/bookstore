package com.gyf.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.domain.Product;
import com.gyf.bookstore.service.ProductService;

@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String id = request.getParameter("id");
		ProductService ps = new ProductService();
		Product book = ps.findBookById(id);
		
		if(book != null){
			request.setAttribute("book", book);
		}
		
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}
}
