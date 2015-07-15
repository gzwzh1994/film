package com.bysj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Entity
public class Hall {
	private Integer hallid; // 影厅编号
	private String hallname; // 影厅名
	private Integer cinemaid; // 影院编号
	private String hallseat; // 影厅座位
	private Integer seatnum;// 座位数量

	@Id
	@GeneratedValue
	public Integer getHallid() {
		return hallid;
	}

	public void setHallid(Integer hallid) {
		this.hallid = hallid;
	}

	public String getHallname() {
		return hallname;
	}

	public void setHallname(String hallname) {
		this.hallname = hallname;
	}

	public Integer getCinemaid() {
		return cinemaid;
	}

	public void setCinemaid(Integer cinemaid) {
		this.cinemaid = cinemaid;
	}

	public String getHallseat() {
		return hallseat;
	}

	public void setHallseat(String hallseat) {
		this.hallseat = hallseat;
	}

	public Integer getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(Integer seatnum) {
		this.seatnum = seatnum;
	}

}
