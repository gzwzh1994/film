package com.bysj.service;

import java.util.List;

import com.bysj.model.User;

/**
 * 
 * @author gzwzh1994
 * 
 */
public interface UserManager {
	public void addUser(User u) throws Exception;

	public void deleteUser(User u) throws Exception;

	public void updateUser(User u) throws Exception;

	public List<User> findAllList();

	public List<User> findAll(String hql);

	public User findUserByPhone(Long phone);

	public User findUserByPhoneAndPassword(Long phone, String userpwd);

	public Integer updateUserhql(String hql, Object[] param);
}
