package com.bysj.dao;

import java.util.List;

public interface VersionDao<T> {
	   /**
	    * 版本查询
	    */
	   public List<T> find(String hql);
	   public List<T> find(String hql, Object[] param);
	   
	   /**
	    * 单个版本查询
	    */
	   public T get(String hql, Object[] param);
}
