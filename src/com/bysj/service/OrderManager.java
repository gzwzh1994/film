package com.bysj.service;

import java.util.List;

import com.bysj.model.Orders;

public interface OrderManager {
	public void addOrder(Orders o) throws Exception;
	public void deleteOrder(Orders o) throws Exception;
	public void updateOrder(Orders o) throws Exception;
	public List<Orders> findAllList(String hql);
	public <T> List<T> findOrder(String hql,Object[] param);
	public <T> List<T> findOrderBy(String hql, Object[] param, Integer page, Integer rows);
	public Object getOrder(String hql,Object[] param);
}
