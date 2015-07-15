package com.bysj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bysj.dao.MoiveDao;
import com.bysj.model.Moive;
import com.bysj.service.MoiveManager;

@Component("moiveManager")
public class MoiveManagerImpl implements MoiveManager {
	
	private MoiveDao<Moive> moivedao;

	@Override
	public void addMoive(Moive moive) throws Exception {
        moivedao.save(moive); 
	}

	@Override
	public void deleteMoive(Moive moive) throws Exception {
		moivedao.delete(moive);
	}

	@Override
	public void updateMoive(Moive moive) throws Exception {
		moivedao.saveOrUpdate(moive);
	}

	@Override
	public List<Moive> findAllList() {
		return moivedao.find("from Moive m");
	}

	@Override
	public <T> List<T> findListByDate(String hql, Object[] param) {
		return  (List<T>) moivedao.find(hql, param);
	}

	@Override
	public Moive findMoiveById(Integer moiveid) {
		return moivedao.get(" from Moive m where m.moiveid=? ", new Object[] { moiveid });
	}
	
	@Override
	public Moive findMoive(String hql, Object[] param) {
		return moivedao.get(hql, param);
	}
	
	public MoiveDao<Moive> getMoivedao() {
		return moivedao;
	}

	@Resource
	public void setMoivedao(MoiveDao<Moive> moivedao) {
		this.moivedao = moivedao;
	}

	

}
