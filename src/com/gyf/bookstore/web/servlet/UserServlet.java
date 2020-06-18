package com.gyf.bookstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.gyf.bookstore.domain.User;
import com.gyf.bookstore.exception.UserException;
import com.gyf.bookstore.service.UserService;

@WebServlet("/user/*")
public class UserServlet extends Base {
	UserService us = new UserService();
	//用户登录
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1.获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			// 2.登录成功
			User user = us.login(username, password);
			String path;
			if ("管理员".equals(user.getRole())) {
				path = "/admin/login/home.jsp";
			} else {// 普通用户
				path = "/index.jsp";
			}
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+path);
//			request.getRequestDispatcher(path).forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 3.登录失败转回登录页面
			request.setAttribute("user_msg", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

	/*** 用户注册*/
	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 验证码
		String checkcode = request.getParameter("checkcode");
		String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
		System.out.println(checkcode);
		System.out.println(checkcode_session);
		System.out.println("哈哈");
		if (!checkcode_session.equals(checkcode)) {
			request.setAttribute("ckcode_msg", "验证码不正确");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}

		// 创建User对象
		User user = new User();
		try {
			// 把参数封装成Bean
			BeanUtils.populate(user, request.getParameterMap());

			// 设置一个激活码
			user.setActiveCode(UUID.randomUUID().toString());
			user.setRole("普通用户");
			user.setState(1);
			// 注册
			UserService us = new UserService();
			us.register(user);
			// 注册成功跳转到成功界面
			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
			System.out.println(user);
		} catch (UserException e) {
			e.printStackTrace();
			// 用户注册失败
			request.setAttribute("user_msg", e.getMessage() + ":用户名存在");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// Bean封装失败
			e.printStackTrace();
		}
}
	 //用户退出
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	//修改用户信息
	protected void modifyUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取请求参数
		User user = new User();
		try {
			//map转模型
			BeanUtils.populate(user, request.getParameterMap());

			//调用业务
			UserService us = new UserService();
			us.modifyUser(user);

			//session失效
			request.getSession().invalidate();

			response.sendRedirect(request.getContextPath() + "/modifyUserInfoSuccess.jsp");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
		}
	}
	//我的账户信息
	protected void myacount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");

		if(user == null){
			//未登录进行登录界面
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}else{
			//进入我的帐号页面
			request.getRequestDispatcher("/myAccount.jsp").forward(request, response);
		}

	}

}
