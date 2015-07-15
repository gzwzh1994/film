package com.bysj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bysj.dao.OrderDao;
import com.bysj.model.Orders;
import com.bysj.service.OrderManager;
@Component("orderManager")
public class OrderManagerImpl implements OrderManager {
	private OrderDao<Orders> orderdao;
	@Override
	public void addOrder(Orders o) throws Exception {
		orderdao.save(o);
	}

	@Override
	public void deleteOrder(Orders o) throws Exception {
		orderdao.delete(o);
	}

	@Override
	public void updateOrder(Orders o) throws Exception {
		orderdao.saveOrUpdate(o);
	}

	@Override
	public List<Orders> findAllList(String hql) {
		return orderdao.find(hql);
	}

	@Override
	public <T> List<T> findOrder(String hql, Object[] param) {
		return (List<T>) orderdao.find(hql, param);
	}

	@Override
	public <T> List<T> findOrderBy(String hql, Object[] param, Integer page,
			Integer rows) {
		return (List<T>) orderdao.find(hql, param, page, rows);
	}

	@Override
	public Object getOrder(String hql, Object[] param) {
		return orderdao.get(hql, param);
	}

	public OrderDao<Orders> getOrderdao() {
		return orderdao;
	}
	@Resource
	public void setOrderdao(OrderDao<Orders> orderdao) {
		this.orderdao = orderdao;
	}

}
