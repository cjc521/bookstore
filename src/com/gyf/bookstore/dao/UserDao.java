package com.gyf.bookstore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.gyf.bookstore.domain.User;
import com.gyf.bookstore.utils.C3P0Utils;

public class UserDao {

	public void add(User user) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "INSERT INTO user " + 
					 "(username,PASSWORD,gender,email,telephone,introduce,activeCode,state,role,registTime) " +
				     "VALUES (?,?,?,?,?,?,?,?,?,?)";
					
		qr.update(sql,user.getUsername(),user.getPassword(),user.getGender(),
				user.getEmail(),user.getTelephone(),user.getIntroduce(),
				user.getActiveCode(),user.getState(),user.getRole(),user.getRegistTime());
	}
	/**
	 * 根据激活码查找用户
	 */
	public User findUserByActionCode(String activeCode) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where activeCode = ?";
		return qr.query(sql, new BeanHandler<User>(User.class), activeCode);
	}

	/**
	 * 根据激活码更新用户我状态为1
	 */
	public void updateActiveState(String activeCode) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update user set state = 1 where activeCode = ?";
		qr.update(sql, activeCode);
	}
	
	/**
	 * 根据用户名查找用户
	 */
	public User findUserByUsernameAndPassword(String username,String password) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		return qr.query(sql, new BeanHandler<User>(User.class), username,password);
	}
	/**
	 * 通过Id查找用户
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public User findUserById(String id)throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where id=?";
		return qr.query(sql, new BeanHandler<User>(User.class),id);
	}
	public void modifyUser(User user)throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update user set password = ?,gender = ? , telephone = ? where id = ?";
		qr.update(sql, user.getPassword(),user.getGender(),user.getTelephone(),user.getId());
	}
}
