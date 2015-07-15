package com.bysj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bysj.dao.CityDao;
import com.bysj.model.City;
import com.bysj.service.CityManager;
@Component("cityManager")
public class CityManagerImpl implements CityManager {
   private CityDao<City> citydao;
	@Override
	public void addCity(City c) throws Exception {
		citydao.save(c);
	}

	@Override
	public void deleteCity(City c) throws Exception {
		citydao.delete(c);
	}

	@Override
	public void updateCity(City c) throws Exception {
		citydao.saveOrUpdate(c);
	}

	@Override
	public List<City> findAllList(String hql) {
		return citydao.find(hql);
	}

	@Override
	public List<City> findCityBy(String hql, Object[] param) {
		return citydao.find(hql, param);
	}

	@Override
	public City findCity(String hql, Object[] param) {
		return citydao.get(hql, param);
	}
	
	public CityDao<City> getCitydao() {
		return citydao;
	}
	@Resource
	public void setCitydao(CityDao<City> citydao) {
		this.citydao = citydao;
	}

}
