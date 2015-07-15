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
public class Orders {
	private Integer orderid; // 订单编号
	private Integer scheduleid; // 放映时刻编号
	private Integer userid; // 用户编号
	private String orderdetails; // 订单详情
	private Integer price; // 价格
	private Integer paysatus; // 订单状态

	@Id
	@GeneratedValue
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(Integer scheduleid) {
		this.scheduleid = scheduleid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(String orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPaysatus() {
		return paysatus;
	}

	public void setPaysatus(Integer paysatus) {
		this.paysatus = paysatus;
	}

}
