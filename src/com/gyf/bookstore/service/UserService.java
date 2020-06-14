package com.gyf.bookstore.service;

import java.sql.SQLException;

import com.gyf.bookstore.dao.UserDao;
import com.gyf.bookstore.domain.User;
import com.gyf.bookstore.exception.UserException;
import com.gyf.bookstore.utils.SendJMail;

public class UserService {

	UserDao ud = new UserDao();
	public void register(User user)throws UserException{
		try {
			//保存到数据库
			ud.add(user);
			//发送激活邮件
			String activeLink = "http://localhost:8080/bookstore/active?activeCode=" + user.getActiveCode();
			String html = "欢迎注册网上书城，请 <a href=\"" + activeLink+ "\">点击</a> 激活帐号";
			//System.out.println("激活:" + html);
			SendJMail.sendMail(user.getEmail(), html);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("注册失败");
		}
	}
	
	/**
	 * 激活用户
	 * @param activeCode 激活码
	 */
	public void activeUser(String activeCode)throws UserException{
		//1.查找是否有对应激活码的用户
		try {
			User user = ud.findUserByActionCode(activeCode);
			//2.如果有，就更新状态为1
			if(user!=null){
				ud.updateActiveState(activeCode);
			}else{
				//3.如果没有，就激活失败
				throw new UserException("激活失败,没有这个激活码");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("激活失败");
		}
	}
	
	/**
	 * 登录
	 */
	public User login(String username,String password)throws UserException{
		
		try {
			User user = ud.findUserByUsernameAndPassword(username, password);
			if(user == null){
				throw new UserException("用户名或者密码不正确 ");
			}
			
			if(user.getState() == 0){
				throw new UserException("用户未激活 ");
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("登录失败 ");
		}
	}

	/**
	 * 通过ID查找用户
	 * @param id
	 * @return
	 * @throws UserException
	 */
	public User findUserById(String id)throws UserException {
		// TODO Auto-generated method stub
		User user = null;
		try {
			user = ud.findUserById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("查不到用户");
		}
		return user;
	}

	public void modifyUser(User user)throws UserException {

		try {
			ud.modifyUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("修改用户失败");
		}
	}
}
