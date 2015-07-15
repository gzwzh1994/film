package com.bysj.service;

import java.util.List;

import com.bysj.model.Admin;
/**
 * 
 * @author gzwzh1994
 *
 */
public interface AdminManager {
	public void addAdmin(Admin a) throws Exception;

	public void deleteAdmin(Admin a) throws Exception;

	public void updateAdmin(Admin a) throws Exception;

	public List<Admin> findAllList();

	public Admin findAdminByName(String adminname);

	public Admin findAdminByNameAndPassword(String adminname, String adminpwd);

	public Integer updateUserhql(String hql, Object[] param);
}
