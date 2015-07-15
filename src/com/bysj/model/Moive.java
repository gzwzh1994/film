package com.bysj.model;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Moive {
	private Integer moiveid; // 电影编号
	private String moivename; // 电影名称
	private String moiveimage; //电影宣传图
	private Date releasetime; // 上映时间
	private String director; // 导演
	private String starring; // 主演
	private String type; // 类型
	private String nation; // 国家
	private String version;//版本
	private String totaltime; // 片长
	private String moiveplot; // 影片剧情
	private String language;// 语言
	private String score;  //评分
    @Id
    @GeneratedValue
	public Integer getMoiveid() {
		return moiveid;
	}

	public void setMoiveid(Integer moiveid) {
		this.moiveid = moiveid;
	}

	public String getMoiveimage() {
		return moiveimage;
	}

	public void setMoiveimage(String moiveimage) {
		this.moiveimage = moiveimage;
	}

	public String getMoivename() {
		return moivename;
	}

	public void setMoivename(String moivename) {
		this.moivename = moivename;
	}

	public Date getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(Date releasetime) {
		this.releasetime = releasetime;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStarring() {
		return starring;
	}

	public void setStarring(String starring) {
		this.starring = starring;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getTotaltime() {
		return totaltime;
	}

	public void setTotaltime(String totaltime) {
		this.totaltime = totaltime;
	}

	public String getMoiveplot() {
		return moiveplot;
	}

	public void setMoiveplot(String moiveplot) {
		this.moiveplot = moiveplot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Moive() {
		super();
	}

	public Moive(Integer moiveid, String moivename) {
		super();
		this.moiveid = moiveid;
		this.moivename = moivename;
	}
    
}
