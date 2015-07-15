package com.bysj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bysj.dao.UserDao;
import com.bysj.model.User;
import com.bysj.service.UserManager;

@Component("userManager")
public class UserManagerImpl implements UserManager {
    
	private UserDao<User> userdao;
	
	@Override
	public void addUser(User u) throws Exception {
		userdao.save(u);      
	}
	
	@Override
	public void deleteUser(User u) throws Exception {
		userdao.delete(u);		
	}

	@Override
	public void updateUser(User u) throws Exception {
		userdao.saveOrUpdate(u);		
	}
	
	public UserDao<User> getUserdao() {
		return userdao;
	}
	@Resource
	public void setUserdao(UserDao<User> userdao) {
		this.userdao = userdao;
	}

	@Override
	public List<User> findAllList() {
		return userdao.find(" from User u");
	}

	@Override
	public List<User> findAll(String hql) {
		return userdao.find(hql);
	}

	@Override
	public User findUserByPhone(Long phone) {
		return userdao.get("from User u where u.phone = ? ", new Object[] { phone });
	}

	@Override
	public User findUserByPhoneAndPassword(Long phone, String userpwd) {
		return userdao.get("from User u where u.phone = ? and u.userpwd = ? ", new Object[] { phone, userpwd });
	}

	@Override
	public Integer updateUserhql(String hql, Object[] param) {
		return userdao.executeHql(hql, param);
	}

	
}
