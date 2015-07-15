package com.bysj.service;

import java.util.List;

import com.bysj.model.City;

public interface CityManager {
	public void addCity(City c) throws Exception;

	public void deleteCity(City c) throws Exception;

	public void updateCity(City c) throws Exception;

	public List<City> findAllList(String hql);
	public List<City> findCityBy(String hql,Object[] param);
	
	public City findCity(String hql,Object[] param);
}
