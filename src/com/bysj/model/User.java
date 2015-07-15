package com.bysj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Entity
public class User {
	private Integer userid; // 用户编号
	private Long phone; // 电话兼用户名
	private String username;// 用户昵称
	private String userpwd; // 用户密码

	@Id
	@GeneratedValue
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public User(Integer userid, Long phone) {
		super();
		this.userid = userid;
		this.phone = phone;
	}

	public User(Integer userid, Long phone, String username) {
		super();
		this.userid = userid;
		this.phone = phone;
		this.username = username;
	}

}
