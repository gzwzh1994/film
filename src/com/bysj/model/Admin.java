package com.bysj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Admin {
	private Integer adminid; // 管理员id
	private String adminname; // 管理员帐号
	private String adminpwd; // 管理员密码

	@Id
	@GeneratedValue
	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getAdminpwd() {
		return adminpwd;
	}

	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}

	public Admin() {
		super();
	}

	public Admin(Integer adminid, String adminname) {
		super();
		this.adminid = adminid;
		this.adminname = adminname;
	}

}
