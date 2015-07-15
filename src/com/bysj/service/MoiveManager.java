package com.bysj.service;

import java.util.List;

import com.bysj.model.Moive;

/**
 * 
 * @author gzwzh1994
 * 
 */
public interface MoiveManager {
	public void addMoive(Moive moive) throws Exception;

	public void deleteMoive(Moive moive) throws Exception;

	public void updateMoive(Moive moive) throws Exception;

	public List<Moive> findAllList();

	public <T> List<T> findListByDate(String hql, Object[] param);

	public Moive findMoiveById(Integer moiveid);

	public Moive findMoive(String hql, Object[] param);
}
