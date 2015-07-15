package com.bysj.vo;

import java.text.SimpleDateFormat;

public class MoiveInfo2 {
	private Integer FilmNo;
	private String CFilmName;
	private String FrontImg;
	private String Duration;// 时长
	private String ShowDate;
	private String ShowTime;
	private Integer ShowType;
	private String Language;
	private Integer CinemaNo;
	private String CinemaName;
	private String HallName;
	private Integer SettlementPrice;
	public Integer getFilmNo() {
		return FilmNo;
	}
	public void setFilmNo(Integer filmNo) {
		FilmNo = filmNo;
	}
	public String getCFilmName() {
		return CFilmName;
	}
	public void setCFilmName(String cFilmName) {
		CFilmName = cFilmName;
	}
	public String getFrontImg() {
		return FrontImg;
	}
	public void setFrontImg(String frontImg) {
		FrontImg = frontImg;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	public String getShowDate() {
		return ShowDate;
	}
	public void setShowDate(String showDate) {
		ShowDate = showDate;
	}
	public String getShowTime() {
		return ShowTime;
	}
	public void setShowTime(String showTime) {
		ShowTime = showTime;
	}
	public Integer getShowType() {
		return ShowType;
	}
	public void setShowType(Integer showType) {
		ShowType = showType;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public Integer getCinemaNo() {
		return CinemaNo;
	}
	public void setCinemaNo(Integer cinemaNo) {
		CinemaNo = cinemaNo;
	}
	public String getCinemaName() {
		return CinemaName;
	}
	public void setCinemaName(String cinemaName) {
		CinemaName = cinemaName;
	}
	public String getHallName() {
		return HallName;
	}
	public void setHallName(String hallName) {
		HallName = hallName;
	}
	
	public Integer getSettlementPrice() {
		return SettlementPrice;
	}
	public void setSettlementPrice(Integer settlementPrice) {
		SettlementPrice = settlementPrice;
	}
	public MoiveInfo2() {
		super();
	}
	public MoiveInfo2(Integer filmNo, String cFilmName, String frontImg,
			String duration, Object showDate, Object showTime,
			Integer showType, String language, Integer cinemaNo,
			String cinemaName, String hallName,Integer settlementPrice) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		FilmNo = filmNo;
		CFilmName = cFilmName;
		FrontImg = frontImg;
		Duration = duration;
		ShowDate = showDate.toString();
		ShowTime = sdf.format(showTime);
		ShowType = showType;
		Language = language;
		CinemaNo = cinemaNo;
		CinemaName = cinemaName;
		HallName = hallName;
		SettlementPrice=settlementPrice;
	}
	
}
