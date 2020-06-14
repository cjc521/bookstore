package com.gyf.bookstore.domain;

import java.util.Date;

public class User {
	private int id; // 用户编号
	private String username; // 用户姓名
	private String password; // 用户密码
	private String gender; // 用户性别
	private String email; // 用户邮箱
	private String telephone; // 用户联系电话
	private String introduce; // 用户介绍
	private String activeCode; // 激活码
	private String role; // 用户角色
	private int state; // 用户状态
	private Date registTime;// 注册时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender
				+ ", email=" + email + ", telephone=" + telephone + ", introduce=" + introduce + ", activeCode="
				+ activeCode + ", role=" + role + ", state=" + state + ", registTime=" + registTime + "]";
	}
	
}
