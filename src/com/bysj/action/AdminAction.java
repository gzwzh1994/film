package com.bysj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bysj.model.Admin;
import com.bysj.service.AdminManager;
import com.bysj.vo.AdminInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Component("adminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport implements
ModelDriven<AdminInfo> {
	private static final long serialVersionUID = 1L;
	private AdminInfo admininfo = new AdminInfo();
	private AdminManager adminManager;
	private Admin admin;
	
	public String login() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
    	HttpSession session=request.getSession();
    	Admin a=null;
    	a=adminManager.findAdminByNameAndPassword(admininfo.getAdminname(), admininfo.getAdminpwd());
    	if(null==a){
    		request.setAttribute("errorTip","登录失败：帐号或密码错误");
    		return "signin";
    	}else{
    		session.setAttribute("admin1", a.getAdminname());
    		return "index";
    	}
	}
	
	public void checkname() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String adminname=request.getParameter("adminname");
		Admin a=null;
		a=adminManager.findAdminByName(adminname);
		if(null==a){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("用户名不存在");
			out.flush();
			out.close();
    	}
	}
	
	public String manage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Admin> adminlist=adminManager.findAllList();
		request.setAttribute("adminlist", adminlist);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String adminname=request.getParameter("adminname");
		if(adminname==null || adminname=="" ){
		}else{
			String adminpwd=request.getParameter("pwd");
			admin=new Admin();
			Admin a=null;
			a=adminManager.findAdminByName(adminname);
			if(null==a){
				admin.setAdminname(adminname);
				admin.setAdminpwd(adminpwd);
				adminManager.addAdmin(admin);
				request.setAttribute("tip", "添加成功");
			}else{
				request.setAttribute("tip", "账号已存在添加失败");
			}
		}
		return SUCCESS;
	}
	
	public String update() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String adminpwd=request.getParameter("pwd");
		if(adminpwd==null || adminpwd==""){
			request.setAttribute("adminname", request.getParameter("adminname"));
		}else{
			Admin a=null;
			a=adminManager.findAdminByNameAndPassword(request.getParameter("adminname"), request.getParameter("oldpwd"));
			if(null==a){
				request.setAttribute("adminname", request.getParameter("adminname"));
				request.setAttribute("tip", "旧密码输入错误修改失败");
			}else{
				a.setAdminpwd(adminpwd);
				adminManager.updateAdmin(a);
				request.setAttribute("tip", "修改成功");
				request.setAttribute("adminname", request.getParameter("adminname"));
			}
		}
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		 HttpServletRequest request = ServletActionContext.getRequest();
		 admin=new Admin();
		 admin.setAdminid(Integer.parseInt(request.getParameter("adminid")));
		 adminManager.deleteAdmin(admin);
		 return "ok";
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
