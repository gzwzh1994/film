package com.bysj.vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 
 * @author gzwzh1994
 * 
 */
public class MoiveIndex {
	private Integer moiveid; // 电影编号
	private String moivename; // 电影名称
	private String releasetime; // 上映时间
	private String score; // 评分
	private String imageadr; // 图片地址

	public Integer getMoiveid() {
		return moiveid;
	}

	public void setMoiveid(Integer moiveid) {
		this.moiveid = moiveid;
	}

	public String getMoivename() {
		return moivename;
	}

	public void setMoivename(String moivename) {
		this.moivename = moivename;
	}

	public String getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getImageadr() {
		return imageadr;
	}

	public void setImageadr(String imageadr) {
		this.imageadr = imageadr;
	}

	public MoiveIndex() {
		super();
	}

	public MoiveIndex(Integer moiveid, String moivename, Object releasetime,
			String score, String imageadr) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		this.moiveid = moiveid;
		this.moivename = moivename;
		this.releasetime = sdf.format(releasetime);
		this.score = score;
		this.imageadr = imageadr;
	}

}
