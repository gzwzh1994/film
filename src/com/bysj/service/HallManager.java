package com.bysj.service;

import java.util.List;

import com.bysj.model.Hall;

/**
 * 
 * @author gzwzh1994
 * 
 */
public interface HallManager {
	public void addHall(Hall hall) throws Exception;

	public void deleteHall(Hall hall) throws Exception;

	public void updateHall(Hall hall) throws Exception;

	public List<Hall> findAllList(String hql);

	public List<Hall> findHallBy(String hql, Object[] param);

	public Hall findHall(String hql, Object[] param);
}
