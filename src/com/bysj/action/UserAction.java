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

import com.bysj.model.User;
import com.bysj.service.UserManager;
import com.bysj.vo.UserInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<UserInfo> {

	private static final long serialVersionUID = 1L;
	private UserInfo userinfo = new UserInfo();
	private UserManager userManage;
	private User user;

	public String reg() throws Exception {
		User u = new User();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String yzm = userinfo.getTxtCheckCode().toLowerCase();
		String authCode = session.getAttribute("authCode").toString()
				.toLowerCase();
		if (!yzm.equals(authCode)) {
			request.setAttribute("errorTip", "验证码输入错误");
			return ERROR;
		} else {
			System.out.println(userinfo.getTxtMobile());
			u.setPhone(Long.parseLong(userinfo.getTxtMobile()));
			u.setUsername(userinfo.getTxtMobile());
			u.setUserpwd(userinfo.getTxtPwd());
			userManage.addUser(u);
			String view = "display:none";
			session.setAttribute("username", u.getUsername());
			session.setAttribute("phone", u.getPhone());
			session.setAttribute("view", view);
			session.setAttribute("view1", "");
			return SUCCESS;
		}
	}

	public String login() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String yzm = userinfo.getTxtCheckCode().toLowerCase();
		String authCode = session.getAttribute("authCode").toString()
				.toLowerCase();
		if (!yzm.equals(authCode)) {
			request.setAttribute("errorTip", "验证码输入错误");
			return ERROR;
		} else {
			User u = null;
			u = userManage.findUserByPhone(Long.parseLong(userinfo
					.getTxtMobile()));
			if (null == u) {
				request.setAttribute("errorTip", "登录失败：用户不存在");
				return ERROR;
			} else {
				u = userManage.findUserByPhoneAndPassword(
						Long.parseLong(userinfo.getTxtMobile()),
						userinfo.getTxtPwd());
				if (null == u) {
					request.setAttribute("errorTip", "登录失败：帐号或密码错误");
					return ERROR;
				}
			}
			String view = "display:none";
			session.setAttribute("username", u.getUsername());
			session.setAttribute("phone", u.getPhone());
			session.setAttribute("view", view);
			session.setAttribute("view1", "");
			return "success1";
		}
	}

	public String quicklogin() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String yzm = userinfo.getTxtCheckCode().toLowerCase();
		String authCode = session.getAttribute("authCode").toString()
				.toLowerCase();
		if (!yzm.equals(authCode)) {
			request.setAttribute("errorTip", "验证码输入错误");
			return ERROR;
		} else {
			User u = null;
			u = userManage.findUserByPhone(Long.parseLong(userinfo
					.getTxtMobile()));
			if (null == u) {
				request.setAttribute("errorTip", "登录失败：用户不存在");
				return ERROR;
			} else {
				u = userManage.findUserByPhoneAndPassword(
						Long.parseLong(userinfo.getTxtMobile()),
						userinfo.getTxtPwd());
				if (null == u) {
					request.setAttribute("errorTip", "登录失败：帐号或密码错误");
					return ERROR;
				}
			}
			String view = "display:none";
			request.setAttribute("errorTip", "登录成功，请关闭本页面并刷新");
			session.setAttribute("username", u.getUsername());
			session.setAttribute("phone", u.getPhone());
			session.setAttribute("view", view);
			session.setAttribute("view1", "");
			return ERROR;
		}
	}

	public String Update() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String oldpwd = userinfo.getTxtLPassWord();
		Object[] param = null;
		if (oldpwd.equals("")) {
			String username = userinfo.getUsername();
			Long phone = (Long) session.getAttribute("phone");
			param = new Object[] { username, phone };
			userManage.updateUserhql(
					"update User u set u.username=? where u.phone=?", param);
			request.setAttribute("msg", "温馨提示: 设置成功");
			request.setAttribute("value", "1");
			session.setAttribute("username", userinfo.getUsername());
		} else {
			String pwd = userinfo.getTxtNewPassWord();
			Long phone = (Long) session.getAttribute("phone");
			user = userManage.findUserByPhone(phone);
			if (user.getUserpwd().equals(oldpwd)) {
				param = new Object[] { pwd, phone };
				userManage.updateUserhql(
						"update User u set u.userpwd=? where u.phone=?", param);
				request.setAttribute("msg1", "密码修改成功");
				request.setAttribute("value", "3");
			}
		}
		return "ok";
	}

	public void userHandler() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		String username = (String) session.getAttribute("username");
		if (username == null) {
			String s = "0";
			PrintWriter out = response.getWriter();
			out.print(s);
			out.flush();
			out.close();
		} else {
			String s = "1";
			PrintWriter out = response.getWriter();
			out.print(s);
			out.flush();
			out.close();
		}
	}

	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("username", null);
		session.setAttribute("phone", null);
		session.setAttribute("view", "");
		session.setAttribute("view1", "display:none");
		return "index";
	}

	public String manage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<User> userlist = userManage
				.findAll("select new User(u.userid,u.phone,u.username) from User u");
		request.setAttribute("userlist", userlist);
		return SUCCESS;
	}

	public UserInfo getUrinfo() {
		return userinfo;
	}

	public void setUrinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public UserManager getUserManage() {
		return userManage;
	}

	@Resource
	public void setUserManage(UserManager userManage) {
		this.userManage = userManage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public UserInfo getModel() {
		return userinfo;
	}

}
