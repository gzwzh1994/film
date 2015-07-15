package com.bysj.service.impl;
/**
 * 
 * @author gzwzh1994
 *
 */
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bysj.dao.AdminDao;
import com.bysj.model.Admin;
import com.bysj.service.AdminManager;

@Component("adminManager")
public class AdminManagerImpl implements AdminManager {
	private AdminDao<Admin> admindao;

	@Override
	public void addAdmin(Admin a) throws Exception {
		admindao.save(a);
	}

	@Override
	public void deleteAdmin(Admin a) throws Exception {
		admindao.delete(a);
	}

	@Override
	public void updateAdmin(Admin a) throws Exception {
		admindao.saveOrUpdate(a);
	}

	@Override
	public List<Admin> findAllList() {
		return admindao
				.find("select new Admin(a.adminid,a.adminname) from Admin a");
	}

	@Override
	public Admin findAdminByName(String adminname) {
		return admindao.get("from Admin a where a.adminname=? ",
				new Object[] { adminname });
	}

	@Override
	public Admin findAdminByNameAndPassword(String adminname, String adminpwd) {
		return admindao.get(
				"from Admin a where a.adminname=? and a.adminpwd=? ",
				new Object[] { adminname, adminpwd });
	}

	@Override
	public Integer updateUserhql(String hql, Object[] param) {
		return admindao.executeHql(hql, param);
	}

	public AdminDao<Admin> getAdmindao() {
		return admindao;
	}

	@Resource
	public void setAdmindao(AdminDao<Admin> admindao) {
		this.admindao = admindao;
	}

}
