package com.bysj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {
	private Integer imageid; // 图片编号
	private String imagename; // 图片名
	private String imageadr; // 图片地址
	private Integer moiveid; // 电影编号

	@Id
	@GeneratedValue
	public Integer getImageid() {
		return imageid;
	}

	public void setImageid(Integer imageid) {
		this.imageid = imageid;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getImageadr() {
		return imageadr;
	}

	public void setImageadr(String imageadr) {
		this.imageadr = imageadr;
	}

	public Integer getMoiveid() {
		return moiveid;
	}

	public void setMoiveid(Integer moiveid) {
		this.moiveid = moiveid;
	}

}
