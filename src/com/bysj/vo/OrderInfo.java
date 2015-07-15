package com.bysj.vo;

import java.text.SimpleDateFormat;
/**
 * 
 * @author gzwzh1994
 *
 */
public class OrderInfo {
	private String imageadr;
	private String moivename;
	private String language;
	private String version;
	private String totaltime;
	private String cinemaname;
	private String showdate;
	private Integer tip;
	private String showtime;
	private Long phone;
	private Integer price;

	
	public String getImageadr() {
		return imageadr;
	}

	public void setImageadr(String imageadr) {
		this.imageadr = imageadr;
	}

	public String getMoivename() {
		return moivename;
	}

	public void setMoivename(String moivename) {
		this.moivename = moivename;
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

	public String getTotaltime() {
		return totaltime;
	}

	public void setTotaltime(String totaltime) {
		this.totaltime = totaltime;
	}

	public String getCinemaname() {
		return cinemaname;
	}

	public void setCinemaname(String cinemaname) {
		this.cinemaname = cinemaname;
	}

	public String getShowdate() {
		return showdate;
	}

	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}
	
	public Integer getTip() {
		return tip;
	}

	public void setTip(Integer tip) {
		this.tip = tip;
	}
	
	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	

	public OrderInfo() {
		super();
	}

	public OrderInfo(String imageadr, String moivename, String language,
			String version, String totaltime, String cinemaname,
			Object showdate, Integer tip, Object showtime, Long phone,
			Integer price) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		this.imageadr = imageadr;
		this.moivename = moivename;
		this.language = language;
		this.version = version;
		this.totaltime = totaltime;
		this.cinemaname = cinemaname;
		this.showdate = sdf.format(showdate);
		this.tip = tip;
		this.showtime = showtime.toString();
		this.phone = phone;
		this.price = price;

	}

}
