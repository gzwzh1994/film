package com.bysj.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bysj.model.Admin;
import com.bysj.service.AdminManager;
import com.bysj.vo.AdminInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author gzwzh1994
 * 
 */
public class LoginAction extends ActionSupport implements
		ModelDriven<AdminInfo> {

	private static final long serialVersionUID = 1L;
	private AdminInfo admininfo = new AdminInfo();
	private AdminManager adminManager;
	private Admin admin;

	public String L() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String adminname = (String) session.getAttribute("admin1");
		if (adminname == "" || adminname == null) {
			return "signin";
		} else {
			return "index";
		}
	}

	public AdminInfo getAdmininfo() {
		return admininfo;
	}

	public void setAdmininfo(AdminInfo admininfo) {
		this.admininfo = admininfo;
	}

	public AdminManager getAdminManager() {
		return adminManager;
	}

	@Resource
	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public AdminInfo getModel() {
		return admininfo;
	}

}
