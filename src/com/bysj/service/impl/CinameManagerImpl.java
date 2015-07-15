package com.bysj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bysj.dao.CinemaDao;
import com.bysj.model.Cinema;
import com.bysj.service.CinemaManager;
@Component("cinemaManager")
public class CinameManagerImpl implements CinemaManager {
    private CinemaDao<Cinema> cinemadao;
	@Override
	public void addCinema(Cinema cinema) throws Exception {
		cinemadao.save(cinema);
	}

	@Override
	public void deleteCinema(Cinema cinema) throws Exception {
		cinemadao.delete(cinema);
	}

	@Override
	public void updateCinema(Cinema cinema) throws Exception {
		cinemadao.saveOrUpdate(cinema);
	}

	@Override
	public List<Cinema> findAllList(String hql) {
		return cinemadao.find(hql);
	}

	@Override
	public List<Cinema> findCinemaBy(String hql, List<Object> param) {
		return cinemadao.find(hql, param);
	}

	public CinemaDao<Cinema> getCinemadao() {
		return cinemadao;
	}
	@Resource
	public void setCinemadao(CinemaDao<Cinema> cinemadao) {
		this.cinemadao = cinemadao;
	}

	@Override
	public Cinema findCinema(String hql, Object[] param) {
		return cinemadao.get(hql, param);
	}

}
