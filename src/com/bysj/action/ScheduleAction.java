package com.bysj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bysj.model.Cinema;
import com.bysj.model.Hall;
import com.bysj.model.Moive;
import com.bysj.model.Schedule;
import com.bysj.model.Version;
import com.bysj.service.CinemaManager;
import com.bysj.service.HallManager;
import com.bysj.service.MoiveManager;
import com.bysj.service.ScheduleManager;
import com.bysj.service.VersionManager;
import com.bysj.vo.MoiveInfo;
import com.bysj.vo.SeatCol;
import com.bysj.vo.SeatRow;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

@Component("scheduleAction")
@Scope("prototype")
public class ScheduleAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private ScheduleManager scheduleManager;
	private HallManager hallManager;
	private CinemaManager cinemaManager;
	private VersionManager versionManager;
	private MoiveManager moiveManager;
	private Moive moive;
	private Version version;
	private Schedule schedule;
	private Hall hall;
	private Cinema cinema;
	
	//获取放映日期选项
	public void showDate() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Schedule> l = scheduleManager
				.findAllList("from Schedule sc group by sc.showdate ");
		List ls = new ArrayList();
		for (Schedule sc : l) {
			ls.add(sc.getShowdate());
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String jsonArray = gs.toJson(ls);
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
		System.out.println(jsonArray);
	}
	
	//获取影城选项
	public void getCinemas() throws ParseException, Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String date = request.getParameter("showDate");
		Date showdate = Date.valueOf(date);
		List l = scheduleManager
				.findScheduleByDate(
						"select new Cinema(c.cinemaid,c.cinemaname) from Schedule sc,Hall h,Cinema c where sc.showdate=? and sc.hallid=h.hallid and h.cinemaid=c.cinemaid group by c.cinemaid ",
						showdate);
		System.out.println(l);
		Gson gs = new Gson();
		String jsonArray = gs.toJson(l);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
		System.out.println(jsonArray);
	}
	
	//获取影片选项
	public void getHotFilms() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String date = request.getParameter("showDate");
		Date showdate = Date.valueOf(date);
		Integer cid = Integer.parseInt(request.getParameter("cinemaNo"));
		String sfid = request.getParameter("filmNo");
		Object[] param = new Object[] {};
		String jsonArray = null;
		Gson gs = new Gson();
		if (sfid == "") {
			param = new Object[] { showdate, cid };
			List l = scheduleManager
					.findScheduleBy(
							"select new Moive(m.moiveid,m.moivename) from Schedule s,Hall h,Cinema c,Moive m where s.showdate=? and c.cinemaid=? and s.hallid=h.hallid and s.moiveid=m.moiveid and h.cinemaid=c.cinemaid group by m.moiveid",
							param);
			jsonArray = gs.toJson(l);
		} else {
			Integer fid = Integer.parseInt(sfid);
			param = new Object[] { showdate, cid, fid };
			List l = scheduleManager
					.findScheduleBy(
							"select new Moive(m.moiveid,m.moivename) from Schedule s,Cinema c,Moive m where s.showdate=? and c.cinemaid=? and m.moiveid=? and c.cinemaid=? and s.hallid=h.hallid and s.moiveid=m.moiveid and h.cinemaid=c.cinemaid group by m.moiveid",
							param);
			jsonArray = gs.toJson(l);
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
		System.out.println(jsonArray);
	}
	
	//获取电影排期
	public void getShowTimes() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String date = request.getParameter("showDate");
		Date showdate = Date.valueOf(date);
		String cinemaNo = request.getParameter("cinemaNo");
		Integer cinemaid = Integer.parseInt(cinemaNo);
		String filmNo = request.getParameter("filmNo");
		String time = request.getParameter("showTime");
		String Type = request.getParameter("showType");
		Object[] param = new Object[] { showdate, cinemaid };
		Gson gs = new Gson();
		String jsonArray = null;
		if (filmNo == "" && time == "" && Type == "") {
			List<MoiveInfo> l = scheduleManager
					.findScheduleBy(
							"select new com.bysj.vo.MoiveInfo(m.moiveid,m.moivename,i.imageadr,m.totaltime,m.score) from Schedule s,Moive m,Hall h,Cinema c,Image i where s.showdate=? and c.cinemaid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and m.moiveid=i.moiveid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' group by m.moiveid ",
							param);
			for (int i = 0; i < l.size(); i++) {
				Integer moiveid = l.get(i).getFilmNo();
				param = new Object[] { showdate, cinemaid, moiveid };
				List<MoiveInfo> l2 = scheduleManager
						.findScheduleBy(
								"select new com.bysj.vo.MoiveInfo(s.scheduleid,s.showtime,v.versionname,m.language,h.hallname,h.seatnum,s.price,s.tip) from Schedule s,Moive m,Hall h,Cinema c,Version v where s.showdate=? and c.cinemaid=? and m.moiveid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and h.cinemaid=c.cinemaid and s.versionid=v.versionid order by s.showtime asc",
								param);
				MoiveInfo mi = new MoiveInfo(l2);
				l.get(i).setShowTimes(l2);
			}
			jsonArray = gs.toJson(l);
		} else if (time == "" && Type == "") {
			Integer moiveid = Integer.parseInt(filmNo);
			param = new Object[] { showdate, cinemaid, moiveid };
			List<MoiveInfo> l = scheduleManager
					.findScheduleBy(
							"select new com.bysj.vo.MoiveInfo(m.moiveid,m.moivename,i.imageadr,m.totaltime,m.score) from Schedule s,Moive m,Hall h,Cinema c,Image i where s.showdate=? and c.cinemaid=? and m.moiveid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and m.moiveid=i.moiveid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' group by m.moiveid ",
							param);
			for (int i = 0; i < l.size(); i++) {
				List<MoiveInfo> l2 = scheduleManager
						.findScheduleBy(
								"select new com.bysj.vo.MoiveInfo(s.scheduleid,s.showtime,v.versionname,m.language,h.hallname,h.seatnum,s.price,s.tip) from Schedule s,Moive m,Hall h,Cinema c,Version v where s.showdate=? and c.cinemaid=? and m.moiveid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and h.cinemaid=c.cinemaid and s.versionid=v.versionid order by s.showtime asc",
								param);
				MoiveInfo mi = new MoiveInfo(l2);
				l.get(i).setShowTimes(l2);
			}
			jsonArray = gs.toJson(l);
		} else if (filmNo == "" && time == "") {
			Integer type = Integer.parseInt(Type);
			List<MoiveInfo> l = scheduleManager
					.findScheduleBy(
							"select new com.bysj.vo.MoiveInfo(m.moiveid,m.moivename,i.imageadr,m.totaltime,m.score) from Schedule s,Moive m,Hall h,Cinema c,Image i where s.showdate=? and c.cinemaid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and m.moiveid=i.moiveid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' group by m.moiveid ",
							param);
			for (int i = 0; i < l.size(); i++) {
				Integer moiveid = l.get(i).getFilmNo();
				param = new Object[] { showdate, cinemaid, moiveid, type };
				List<MoiveInfo> l2 = scheduleManager
						.findScheduleBy(
								"select new com.bysj.vo.MoiveInfo(s.scheduleid,s.showtime,v.versionname,m.language,h.hallname,h.seatnum,s.price,s.tip) from Schedule s,Moive m,Hall h,Cinema c,Version v where s.showdate=? and c.cinemaid=? and m.moiveid=? and v.versionid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and h.cinemaid=c.cinemaid and s.versionid=v.versionid order by s.showtime asc",
								param);
				MoiveInfo mi = new MoiveInfo(l2);
				l.get(i).setShowTimes(l2);
			}
			jsonArray = gs.toJson(l);
		} else if (filmNo == "" && Type == "") {
			String[] t = time.split("-");
			String t1 = t[0] + ":00";
			String t2 = t[1] + ":00";
			Time showtime1 = Time.valueOf(t1);
			Time showtime2 = Time.valueOf(t2);
			List<MoiveInfo> l = scheduleManager
					.findScheduleBy(
							"select new com.bysj.vo.MoiveInfo(m.moiveid,m.moivename,i.imageadr,m.totaltime,m.score) from Schedule s,Moive m,Hall h,Cinema c,Image i where s.showdate=? and c.cinemaid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and m.moiveid=i.moiveid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' group by m.moiveid ",
							param);
			for (int i = 0; i < l.size(); i++) {
				Integer moiveid = l.get(i).getFilmNo();
				param = new Object[] { showdate, cinemaid, moiveid, showtime1,
						showtime2 };
				List<MoiveInfo> l2 = scheduleManager
						.findScheduleBy(
								"select new com.bysj.vo.MoiveInfo(s.scheduleid,s.showtime,v.versionname,m.language,h.hallname,h.seatnum,s.price,s.tip) from Schedule s,Moive m,Hall h,Cinema c,Version v where s.showdate=? and c.cinemaid=? and m.moiveid=? and s.showtime between ? and ? and s.moiveid=m.moiveid and s.hallid=h.hallid and h.cinemaid=c.cinemaid and s.versionid=v.versionid order by s.showtime asc",
								param);
				MoiveInfo mi = new MoiveInfo(l2);
				l.get(i).setShowTimes(l2);
			}
			jsonArray = gs.toJson(l);
		} else if (Type == "") {
			Integer moiveid = Integer.parseInt(filmNo);
			String[] t = time.split("-");
			String t1 = t[0] + ":00";
			String t2 = t[1] + ":00";
			Time showtime1 = Time.valueOf(t1);
			Time showtime2 = Time.valueOf(t2);
			param = new Object[] { showdate, cinemaid, moiveid };
			List<MoiveInfo> l = scheduleManager
					.findScheduleBy(
							"select new com.bysj.vo.MoiveInfo(m.moiveid,m.moivename,i.imageadr,m.totaltime,m.score) from Schedule s,Moive m,Hall h,Cinema c,Image i where s.showdate=? and c.cinemaid=? and m.moiveid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and m.moiveid=i.moiveid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' group by m.moiveid ",
							param);
			for (int i = 0; i < l.size(); i++) {
				param = new Object[] { showdate, cinemaid, moiveid, showtime1,
						showtime2 };
				List<MoiveInfo> l2 = scheduleManager
						.findScheduleBy(
								"select new com.bysj.vo.MoiveInfo(s.scheduleid,s.showtime,v.versionname,m.language,h.hallname,h.seatnum,s.price,s.tip) from Schedule s,Moive m,Hall h,Cinema c,Version v where s.showdate=? and c.cinemaid=? and m.moiveid=? and s.showtime between ? and ? and s.moiveid=m.moiveid and s.hallid=h.hallid and h.cinemaid=c.cinemaid and s.versionid=v.versionid order by s.showtime asc",
								param);
				MoiveInfo mi = new MoiveInfo(l2);
				l.get(i).setShowTimes(l2);
			}
			jsonArray = gs.toJson(l);
		} else if (time == "") {
			Integer moiveid = Integer.parseInt(filmNo);
			Integer type = Integer.parseInt(Type);
			param = new Object[] { showdate, cinemaid, moiveid };
			List<MoiveInfo> l = scheduleManager
					.findScheduleBy(
							"select new com.bysj.vo.MoiveInfo(m.moiveid,m.moivename,i.imageadr,m.totaltime,m.score) from Schedule s,Moive m,Hall h,Cinema c,Image i where s.showdate=? and c.cinemaid=? and m.moiveid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and m.moiveid=i.moiveid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' group by m.moiveid ",
							param);
			for (int i = 0; i < l.size(); i++) {
				param = new Object[] { showdate, cinemaid, moiveid, type };
				List<MoiveInfo> l2 = scheduleManager
						.findScheduleBy(
								"select new com.bysj.vo.MoiveInfo(s.scheduleid,s.showtime,v.versionname,m.language,h.hallname,h.seatnum,s.price,s.tip) from Schedule s,Moive m,Hall h,Cinema c,Version v where s.showdate=? and c.cinemaid=? and m.moiveid=? and v.versionid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and h.cinemaid=c.cinemaid and s.versionid=v.versionid order by s.showtime asc",
								param);
				MoiveInfo mi = new MoiveInfo(l2);
				l.get(i).setShowTimes(l2);
			}
			jsonArray = gs.toJson(l);
		} else if (filmNo == "") {
			String[] t = time.split("-");
			String t1 = t[0] + ":00";
			String t2 = t[1] + ":00";
			Time showtime1 = Time.valueOf(t1);
			Time showtime2 = Time.valueOf(t2);
			Integer type = Integer.parseInt(Type);
			List<MoiveInfo> l = scheduleManager
					.findScheduleBy(
							"select new com.bysj.vo.MoiveInfo(m.moiveid,m.moivename,i.imageadr,m.totaltime,m.score) from Schedule s,Moive m,Hall h,Cinema c,Image i where s.showdate=? and c.cinemaid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and m.moiveid=i.moiveid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' group by m.moiveid ",
							param);
			for (int i = 0; i < l.size(); i++) {
				Integer moiveid = l.get(i).getFilmNo();
				param = new Object[] { showdate, cinemaid, moiveid, showtime1,
						showtime2, type };
				List<MoiveInfo> l2 = scheduleManager
						.findScheduleBy(
								"select new com.bysj.vo.MoiveInfo(s.scheduleid,s.showtime,v.versionname,m.language,h.hallname,h.seatnum,s.price,s.tip) from Schedule s,Moive m,Hall h,Cinema c,Version v where s.showdate=? and c.cinemaid=? and m.moiveid=? and s.showtime between ? and ? and v.versionid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and h.cinemaid=c.cinemaid and s.versionid=v.versionid order by s.showtime asc",
								param);
				MoiveInfo mi = new MoiveInfo(l2);
				l.get(i).setShowTimes(l2);
			}
			jsonArray = gs.toJson(l);
		} else {
			Integer moiveid = Integer.parseInt(filmNo);
			String[] t = time.split("-");
			String t1 = t[0] + ":00";
			String t2 = t[1] + ":00";
			Time showtime1 = Time.valueOf(t1);
			Time showtime2 = Time.valueOf(t2);
			Integer type = Integer.parseInt(Type);
			param = new Object[] { showdate, cinemaid, moiveid };
			List<MoiveInfo> l = scheduleManager
					.findScheduleBy(
							"select new com.bysj.vo.MoiveInfo(m.moiveid,m.moivename,i.imageadr,m.totaltime,m.score) from Schedule s,Moive m,Hall h,Cinema c,Image i where s.showdate=? and c.cinemaid=? and m.moiveid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and m.moiveid=i.moiveid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' group by m.moiveid ",
							param);
			for (int i = 0; i < l.size(); i++) {
				param = new Object[] { showdate, cinemaid, moiveid, showtime1,
						showtime2, type };
				List<MoiveInfo> l2 = scheduleManager
						.findScheduleBy(
								"select new com.bysj.vo.MoiveInfo(s.scheduleid,s.showtime,v.versionname,m.language,h.hallname,h.seatnum,s.price,s.tip) from Schedule s,Moive m,Hall h,Cinema c,Version v where s.showdate=? and c.cinemaid=? and m.moiveid=? and s.showtime between ? and ? and v.versionid=? and s.moiveid=m.moiveid and s.hallid=h.hallid and h.cinemaid=c.cinemaid and s.versionid=v.versionid order by s.showtime asc",
								param);
				MoiveInfo mi = new MoiveInfo(l2);
				l.get(i).setShowTimes(l2);
			}
			jsonArray = gs.toJson(l);
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
		System.out.println(jsonArray);
	}
	//获取排期影片信息
	public void GetShowFilm() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String scheduleNO=request.getParameter("seqNo");
		Integer scheduleid=Integer.parseInt(scheduleNO);
		Object[] param = new Object[] { scheduleid };
		List<MoiveInfo> l=scheduleManager.findScheduleBy("select new com.bysj.vo.MoiveInfo2(m.moiveid,m.moivename,i.imageadr,m.totaltime,s.showdate,s.showtime,s.versionid,m.language,c.cinemaid,c.cinemaname,h.hallname,s.price) from Schedule s,Moive m,Hall h,Cinema c,Image i where s.scheduleid=? and s.moiveid=m.moiveid and m.moiveid=i.moiveid and s.hallid=h.hallid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' ", param);
		Gson gs = new Gson();		
		String jsonArray =gs.toJson(l.get(0));
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
		System.out.println(jsonArray);
	}
	//选座页面更换场次
	public void GetShowDateTimeByFilmAndCinemaNo() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Integer cinemaid=Integer.parseInt(request.getParameter("cinemaNo"));
		Integer moiveid=Integer.parseInt(request.getParameter("filmNo"));
		Object[] param = new Object[] { cinemaid,moiveid };
		List<MoiveInfo> l=scheduleManager.findScheduleBy("select new com.bysj.vo.MoiveInfo(s.showdate,s.showtime) from Schedule s,Hall h,Cinema c where c.cinemaid=? and s.moiveid=? and s.hallid=h.hallid and h.cinemaid=c.cinemaid ", param);
		Gson gs = new Gson();		
		String jsonArray =gs.toJson(l);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
		System.out.println(jsonArray);
	}
	//获得座位图集合
	public void GetHallSeatsStatus() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String scheduleNO=request.getParameter("seqNo");
		Integer scheduleid=Integer.parseInt(scheduleNO);
		Object[] param = new Object[] { scheduleid };
		 hall=(Hall) scheduleManager.getScheduleBy("select h from Schedule s,Hall h where s.scheduleid=? and s.hallid=h.hallid", param);
		String hallseat=hall.getHallseat();
		String[] s=hallseat.split(":");
		List l = new ArrayList();
		for (int i = 0; i < s.length; i++) {
			SeatRow sr = new SeatRow();
			List ls = new ArrayList();
			Integer z = 0;
			String seat = s[i];
			sr.setRowName("" + (i + 1));
			sr.setRowNo(i + 1);
			sr.setSectionId(hall.getHallid().toString());
			for (int j = 0; j < s[i].length(); j++) {
				SeatCol sc = new SeatCol();
				char Cno = seat.charAt(j);
				sc.setColumnId(j + 1);
				if (Cno == '1') {   //已售
					z = z + 1;
					sc.setColumnNo(z.toString());
					sc.setSeatType(1);
					sc.setStatus(1);
				} else if (Cno == '0' || Cno == '3') {  //空
					sc.setColumnNo("0");
					sc.setSeatType(3);
					sc.setStatus(0);
				} else if (Cno == '2') {   //可选座位
					z = z + 1;
					sc.setColumnNo(z.toString());
					sc.setSeatType(2);
					sc.setStatus(0);
				} else if (Cno == '4') {   //座位维修
					z = z + 1;
					sc.setColumnNo(z.toString());
					sc.setSeatType(4);
					sc.setStatus(1);
				} else if (Cno == '5' || Cno == '6') {   
					z = z + 1;
					sc.setColumnNo(z.toString());
					sc.setSeatType(5);
					sc.setStatus(0);
				}
				sc.setRowId(sr.getRowNo());
				sc.setRowNo(sr.getRowName());
				sc.setSeatNo(sr.getSectionId() + "_" + sr.getRowName() + "_"
						+ sc.getColumnNo());
				ls.add(sc);
			}
			sr.setRowSeats(ls);
			l.add(sr);
		}		
		Gson gs = new Gson();		
		String jsonArray =gs.toJson(l);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
		System.out.println(jsonArray);
	}
	
	public String manage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Cinema> cinemalist=cinemaManager.findAllList("from Cinema");
		List<Hall> halllist=hallManager.findAllList("from Hall");
		List<Version> versionlist=versionManager.findAllList();
		List<Schedule> schedulelist=scheduleManager.findAllList("from Schedule");
		List<Moive> moivelist=moiveManager.findAllList();
		request.setAttribute("cinemalist", cinemalist);
		request.setAttribute("halllist", halllist);
		request.setAttribute("versionlist", versionlist);
		request.setAttribute("schedulelist", schedulelist);
		request.setAttribute("moivelist", moivelist);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String showdate=request.getParameter("showdate");
		if(showdate==null || showdate==""){
			List<Cinema> cinemalist=cinemaManager.findAllList("from Cinema");
			List<Hall> halllist=hallManager.findAllList("from Hall");
			List<Version> versionlist=versionManager.findAllList();
			List<Moive> moivelist=moiveManager.findAllList();
			request.setAttribute("cinemalist", cinemalist);
			request.setAttribute("versionlist", versionlist);
			request.setAttribute("moivelist", moivelist);
			Gson gs = new Gson();		
			String jsonArray =gs.toJson(halllist);
			request.setAttribute("jsonArray", jsonArray);
		}else{
			Schedule s=null;
			String time=request.getParameter("showtime");
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
			java.util.Date d = null;
			try {
				d=sdf.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Time showtime = new Time(d.getTime());
			Object[] param={Date.valueOf(showdate),showtime,Integer.parseInt(request.getParameter("hallid"))};
			s=(Schedule) scheduleManager.getScheduleBy("from Schedule s where s.showdate=? and s.showtime=? and s.hallid=?", param);
			if(null==s){
				schedule=new Schedule();
				schedule.setShowdate(Date.valueOf(showdate));
				schedule.setShowtime(showtime);
				schedule.setHallid(Integer.parseInt(request.getParameter("hallid")));
				schedule.setMoiveid(Integer.parseInt(request.getParameter("moiveid")));
				schedule.setVersionid(Integer.parseInt(request.getParameter("versionid")));
				schedule.setPrice(Integer.parseInt(request.getParameter("price")));
				schedule.setTip(Integer.parseInt(request.getParameter("tip")));
				scheduleManager.addSchedule(schedule);
				request.setAttribute("tip", "添加成功");
			}else{
				List<Cinema> cinemalist=cinemaManager.findAllList("from Cinema");
				List<Hall> halllist=hallManager.findAllList("from Hall");
				List<Version> versionlist=versionManager.findAllList();
				List<Moive> moivelist=moiveManager.findAllList();
				request.setAttribute("cinemalist", cinemalist);
				request.setAttribute("versionlist", versionlist);
				request.setAttribute("moivelist", moivelist);
				Gson gs = new Gson();		
				String jsonArray =gs.toJson(halllist);
				request.setAttribute("jsonArray", jsonArray);
				request.setAttribute("tip", "排期已存在添加失败");
			}
		}
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		 HttpServletRequest request = ServletActionContext.getRequest();
		 schedule=new Schedule();
		 schedule.setScheduleid(Integer.parseInt(request.getParameter("scheduleid")));
		 scheduleManager.deleteSchedule(schedule);
		 return "ok";
	}
	
	public ScheduleManager getScheduleManager() {
		return scheduleManager;
	}

	@Resource
	public void setScheduleManager(ScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public HallManager getHallManager() {
		return hallManager;
	}

	@Resource
	public void setHallManager(HallManager hallManager) {
		this.hallManager = hallManager;
	}

	public CinemaManager getCinemaManager() {
		return cinemaManager;
	}

	@Resource
	public void setCinemaManager(CinemaManager cinemaManager) {
		this.cinemaManager = cinemaManager;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public VersionManager getVersionManager() {
		return versionManager;
	}
	@Resource
	public void setVersionManager(VersionManager versionManager) {
		this.versionManager = versionManager;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public MoiveManager getMoiveManager() {
		return moiveManager;
	}
	@Resource
	public void setMoiveManager(MoiveManager moiveManager) {
		this.moiveManager = moiveManager;
	}

	public Moive getMoive() {
		return moive;
	}

	public void setMoive(Moive moive) {
		this.moive = moive;
	}

}
