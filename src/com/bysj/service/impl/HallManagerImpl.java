package com.bysj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bysj.dao.HallDao;
import com.bysj.model.Hall;
import com.bysj.service.HallManager;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Component("hallManager")
public class HallManagerImpl implements HallManager {
	private HallDao<Hall> halldao;

	@Override
	public void addHall(Hall hall) throws Exception {
		halldao.save(hall);
	}

	@Override
	public void deleteHall(Hall hall) throws Exception {
		halldao.delete(hall);
	}

	@Override
	public void updateHall(Hall hall) throws Exception {
		halldao.saveOrUpdate(hall);
	}

	@Override
	public List<Hall> findAllList(String hql) {
		return halldao.find(hql);
	}

	@Override
	public List<Hall> findHallBy(String hql, Object[] param) {
		return halldao.find(hql, param);
	}

	@Override
	public Hall findHall(String hql, Object[] param) {
		return halldao.get(hql, param);
	}

	public HallDao<Hall> getHalldao() {
		return halldao;
	}

	@Resource
	public void setHalldao(HallDao<Hall> halldao) {
		this.halldao = halldao;
	}

}
