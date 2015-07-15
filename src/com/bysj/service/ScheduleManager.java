package com.bysj.service;

import java.sql.Date;
import java.util.List;

import com.bysj.model.Schedule;

public interface ScheduleManager {
	public void addSchedule(Schedule schedule) throws Exception;

	public void deleteSchedule(Schedule schedule) throws Exception;

	public void updateSchedule(Schedule schedule) throws Exception;

	public List<Schedule> findAllList(String hql);
	
	public List<Schedule> findScheduleByDate(String hql,Date showdate);
	public <T> List<T> findScheduleBy(String hql,Object[] param);
	public Object getScheduleBy(String hql,Object[] param);
}
