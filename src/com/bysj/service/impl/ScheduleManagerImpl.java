package com.bysj.service.impl;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bysj.dao.ScheduleDao;
import com.bysj.model.Schedule;
import com.bysj.service.ScheduleManager;
@Component("scheduleManager")
public class ScheduleManagerImpl implements ScheduleManager {
	private ScheduleDao<Schedule> scheduledao;
	@Override
	public void addSchedule(Schedule schedule) throws Exception {
		scheduledao.save(schedule);
	}

	@Override
	public void deleteSchedule(Schedule schedule) throws Exception {
		scheduledao.delete(schedule);
	}

	@Override
	public void updateSchedule(Schedule schedule) throws Exception {
		scheduledao.saveOrUpdate(schedule);
	}

	@Override
	public List<Schedule> findAllList(String hql) {
		return scheduledao.find(hql);
	}

	@Override
	public List<Schedule> findScheduleByDate(String hql, Date showdate) {
		return scheduledao.find(hql, new Object[] { showdate });
	}

	@Override
	public <T> List<T> findScheduleBy(String hql, Object[] param) {
		return (List<T>) scheduledao.find(hql, param);
	}

	@Override
	public Object getScheduleBy(String hql, Object[] param) {
		return scheduledao.get(hql, param);
	}
	
	public ScheduleDao<Schedule> getScheduledao() {
		return scheduledao;
	}
	@Resource
	public void setScheduledao(ScheduleDao<Schedule> scheduledao) {
		this.scheduledao = scheduledao;
	}

}
