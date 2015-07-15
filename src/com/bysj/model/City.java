package com.bysj.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Entity
public class City {
	private Integer cityid; // 城市编号
	private String cityname; // 城市名

	@Id
	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

}
