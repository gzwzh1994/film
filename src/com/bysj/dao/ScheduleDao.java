package com.bysj.dao;

import java.util.List;

public interface ScheduleDao<T> {
	/**
	 * 添加放映时间
	 */
   public void save(T o);
   
   /**
    * 删除放映时间
    */
   public void delete(T o);
   
   /**
    * 修改放映时间
    */
   public void saveOrUpdate(T o);
   
   /**
    * 放映时间查询
    */
   public List<T> find(String hql);
   public List<T> find(String hql, Object[] param);
   
   /**
    * 单个放映时间查询
    */
   public T get(String hql, Object[] param);
}
