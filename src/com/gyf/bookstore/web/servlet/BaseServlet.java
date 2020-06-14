package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {


		//1.方法名
		String action = request.getParameter("action");
		
		try {
			//2.通过反制获取方法名
			Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			method.setAccessible(true);
			//3.调用方法
			method.invoke(this,request,response);
		} catch (NoSuchMethodException  e) {
			e.printStackTrace();
		}catch (SecurityException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}

