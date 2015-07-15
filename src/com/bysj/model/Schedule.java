package com.bysj.model;

import java.sql.Time;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Schedule {
    private Integer scheduleid;  //放映时刻编号
    private Date showdate;   //放映日期
    private Time showtime;   //放映时间
    private Integer moiveid; //电影编号
    private Integer hallid;  //影厅编号
    private Integer versionid;//版本编号
    private Integer price;  //售价
    private Integer tip;    //服务费
    
    @Id
    @GeneratedValue
	public Integer getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(Integer scheduleid) {
		this.scheduleid = scheduleid;
	}
	public Date getShowdate() {
		return showdate;
	}
	public void setShowdate(Date showdate) {
		this.showdate = showdate;
	}
	public Time getShowtime() {
		return showtime;
	}
	public void setShowtime(Time showtime) {
		this.showtime = showtime;
	}
	public Integer getMoiveid() {
		return moiveid;
	}
	public void setMoiveid(Integer moiveid) {
		this.moiveid = moiveid;
	}
	
	public Integer getHallid() {
		return hallid;
	}
	public void setHallid(Integer hallid) {
		this.hallid = hallid;
	}
	public Integer getVersionid() {
		return versionid;
	}
	public void setVersionid(Integer versionid) {
		this.versionid = versionid;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getTip() {
		return tip;
	}
	public void setTip(Integer tip) {
		this.tip = tip;
	}
    
}
