package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyf.bookstore.domain.Product;
import com.gyf.bookstore.service.ProductService;

@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.根据id查找图书
		String idstr = request.getParameter("id");
		int id = Integer.parseInt(idstr);
		ProductService ps = new ProductService();
		Product book = ps.findBookById(id);
		
		//2.从session中获取购物车信息
		@SuppressWarnings("unchecked")
		Map<Product,Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		
		//3.如果是空，创建一个购物边集合
		if(cart == null){
			cart = new HashMap<Product,Integer>();
		}
		
		//4.判断购物车中是否有当前书的记录
		if(cart.containsKey(book)){
			cart.put(book, cart.get(book) + 1);
		}else{
			cart.put(book, 1);
		}
		System.out.println("购物车信息:" + cart);
		
		//5.重新保存到session中
		request.getSession().setAttribute("cart", cart);
		
		//6.继续购物或者结算查看购物车
		String html = "<a href='"+ request.getContextPath() +"/showProductByPage'>继续购物</a>";
		html += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='" + request.getContextPath()  + "/cart.jsp'>查看购物车</a>";
		response.getWriter().write(html);
	}
}
