package com.bysj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Cinema {
  private Integer cinemaid;   //电影院编号
  private String cinemaname;  //电影院名
  private Integer cityid;     //城市编号
  private String address;     //地址
  private String telephone;   //电话
  private String routes;      //交通路线
  private String preferentialinfo;  //优惠信息
  
	@Id
	@GeneratedValue
	public Integer getCinemaid() {
		return cinemaid;
	}

	public void setCinemaid(Integer cinemaid) {
		this.cinemaid = cinemaid;
	}

	public String getCinemaname() {
		return cinemaname;
	}

	public void setCinemaname(String cinemaname) {
		this.cinemaname = cinemaname;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRoutes() {
		return routes;
	}

	public void setRoutes(String routes) {
		this.routes = routes;
	}

	public String getPreferentialinfo() {
		return preferentialinfo;
	}

	public void setPreferentialinfo(String preferentialinfo) {
		this.preferentialinfo = preferentialinfo;
	}
	

	public Cinema() {
		super();
	}

	public Cinema(Integer cinemaid, String cinemaname) {
		super();
		this.cinemaid = cinemaid;
		this.cinemaname = cinemaname;
	}

	public Cinema(Integer cinemaid, String cinemaname, Integer cityid,
			String address, String telephone, String routes,
			String preferentialinfo) {
		super();
		this.cinemaid = cinemaid;
		this.cinemaname = cinemaname;
		this.cityid = cityid;
		this.address = address;
		this.telephone = telephone;
		this.routes = routes;
		this.preferentialinfo = preferentialinfo;
	}

	

}
