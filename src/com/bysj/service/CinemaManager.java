package com.bysj.service;

import java.util.List;

import com.bysj.model.Cinema;

public interface CinemaManager {
	public void addCinema(Cinema cinema) throws Exception;

	public void deleteCinema(Cinema cinema) throws Exception;

	public void updateCinema(Cinema cinema) throws Exception;

	public List<Cinema> findAllList(String hql);
	public List<Cinema> findCinemaBy(String hql,List<Object> param);
	public Cinema findCinema(String hql,Object[] param);
}
