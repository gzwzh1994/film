package com.bysj.vo;


import java.text.SimpleDateFormat;
import java.util.List;

public class MoiveInfo {
	private Integer FilmNo;
	private Integer SeqNo;
	private String CFilmName;
	private String FrontImg;
	private String Duration;// 时长
	private String ShowDate;
	private String ShowTime;
	private Integer ShowType;
	private String ShowTypeName;
	private String Language;
	private Integer CinemaNo;
	private String CinemaName;
	private String HallName;
	private String AverageDegree;
	private Integer SeatNum;
	private Integer SettlementPrice;
	private Integer UnionCharge;
	private Integer CinemaPrice;
	private List<?> ShowTimes;
	
	public Integer getFilmNo() {
		return FilmNo;
	}
	public void setFilmNo(Integer filmNo) {
		FilmNo = filmNo;
	}
	
	public Integer getSeqNo() {
		return SeqNo;
	}
	public void setSeqNo(Integer seqNo) {
		SeqNo = seqNo;
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
	
	public String getShowTime() {
		return ShowTime;
	}
	public void setShowTime(String showTime) {
		ShowTime = showTime;
	}
	public String getShowTypeName() {
		return ShowTypeName;
	}
	public void setShowTypeName(String showTypeName) {
		ShowTypeName = showTypeName;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public String getHallName() {
		return HallName;
	}
	public void setHallName(String hallName) {
		HallName = hallName;
	}
	
	public String getShowDate() {
		return ShowDate;
	}
	public void setShowDate(String showDate) {
		ShowDate = showDate;
	}
	public Integer getShowType() {
		return ShowType;
	}
	public void setShowType(Integer showType) {
		ShowType = showType;
	}
	public String getCinemaName() {
		return CinemaName;
	}
	public void setCinemaName(String cinemaName) {
		CinemaName = cinemaName;
	}
	public List<?> getShowTimes() {
		return ShowTimes;
	}
	public void setShowTimes(List<?> showTimes) {
		ShowTimes = showTimes;
	}
	
	public Integer getCinemaNo() {
		return CinemaNo;
	}
	public void setCinemaNo(Integer cinemaNo) {
		CinemaNo = cinemaNo;
	}
	
	public String getAverageDegree() {
		return AverageDegree;
	}
	public void setAverageDegree(String averageDegree) {
		AverageDegree = averageDegree;
	}
	
	public Integer getSeatNum() {
		return SeatNum;
	}
	public void setSeatNum(Integer seatNum) {
		SeatNum = seatNum;
	}
	
	public Integer getSettlementPrice() {
		return SettlementPrice;
	}
	public void setSettlementPrice(Integer settlementPrice) {
		SettlementPrice = settlementPrice;
	}
	public Integer getUnionCharge() {
		return UnionCharge;
	}
	public void setUnionCharge(Integer unionCharge) {
		UnionCharge = unionCharge;
	}
	public Integer getCinemaPrice() {
		return CinemaPrice;
	}
	public void setCinemaPrice(Integer cinemaPrice) {
		CinemaPrice = cinemaPrice;
	}
	public MoiveInfo() {
		super();
	}

	public MoiveInfo(Integer filmNo, String cFilmName, String frontImg,
			String duration,String averageDegree) {
		super();
		FilmNo = filmNo;
		CFilmName = cFilmName;
		FrontImg = frontImg;
		Duration = duration;
		AverageDegree=averageDegree;
	}
	public MoiveInfo(String showTypeName, String language,
			String hallName) {
		super();
		ShowTypeName = showTypeName;
		Language = language;
		HallName = hallName;
	}
	
	public MoiveInfo(Integer seqNo, Object showTime, String showTypeName, String language,
			String hallName,Integer seatnum,Integer settlementPrice,Integer unionCharge) {
		super();
		SeqNo=seqNo;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		ShowTime = sdf.format(showTime);
		ShowTypeName = showTypeName;
		Language = language;
		HallName = hallName;
		SeatNum = seatnum;
		SettlementPrice=settlementPrice;
		UnionCharge=unionCharge;
		CinemaPrice=settlementPrice*2;
		
	}
	public MoiveInfo(List<?> showTimes) {
		super();
		ShowTimes = showTimes;
	}
	
	public MoiveInfo(Object showDate, Object showTime) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		ShowDate = showDate.toString();
		ShowTime = sdf.format(showTime);
	}
	
}
