package com.cjc.bookstore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjc.bookstore.domain.Product;
import com.cjc.bookstore.service.ProductService;

@WebServlet("/changeNum")
public class ChangeNumServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.获取请求参数
		String idstr = request.getParameter("id");
		int id = Integer.parseInt(idstr);
		String numstr = request.getParameter("num");
		int num = Integer.parseInt(numstr);
		//2.只需要封装个对象，设置个ID即可
		ProductService ps = new ProductService();
		Product book = ps.findBookById(id);
		if(book == null){
			response.getWriter().write("非法访问");return;
		}
		Map<Product,Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		if(cart == null){
			response.getWriter().write("非法访问");return;
		}
		
		if(num==0){
			cart.remove(book);//把这个产品从session中移除
		}else{
			if(cart.containsKey(book)){//更改数量
				cart.put(book, num);
			}
		}
		request.setAttribute("cart", cart);
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
}
