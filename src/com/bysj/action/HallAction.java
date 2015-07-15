package com.bysj.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bysj.model.Cinema;
import com.bysj.model.City;
import com.bysj.model.Hall;
import com.bysj.service.CinemaManager;
import com.bysj.service.CityManager;
import com.bysj.service.HallManager;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Component("hallAction")
@Scope("prototype")
public class HallAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private CinemaManager cinemaManager;
	private Cinema cinema = new Cinema();
	private HallManager hallManager;
	private Hall hall = new Hall();

	public String manage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Cinema> cinemalist = cinemaManager.findAllList("from Cinema");
		List<Hall> halllist = hallManager.findAllList("from Hall");
		request.setAttribute("cinemalist", cinemalist);
		request.setAttribute("halllist", halllist);
		return SUCCESS;
	}

	public String add() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String hallname = request.getParameter("hallname");
		if (hallname == null || hallname == "") {
			List<Cinema> cinemalist = cinemaManager.findAllList("from Cinema");
			request.setAttribute("cinemalist", cinemalist);
		} else {
			Integer cinemaid = Integer.parseInt(request
					.getParameter("cinemaid"));
			Hall h = null;
			Object[] param = { hallname, cinemaid };
			h = hallManager.findHall(
					"from Hall h where h.hallname=? and h.cinemaid=?", param);
			if (null == h) {
				hall.setHallname(hallname);
				hall.setCinemaid(cinemaid);
				hall.setSeatnum(0);
				hallManager.addHall(hall);
				request.setAttribute("tip", "添加成功");
				List<Cinema> cinemalist = cinemaManager
						.findAllList("from Cinema");
				request.setAttribute("cinemalist", cinemalist);
			} else {
				request.setAttribute("tip", "影厅已存在添加失败");
				List<Cinema> cinemalist = cinemaManager
						.findAllList("from Cinema");
				request.setAttribute("cinemalist", cinemalist);
			}
		}
		return SUCCESS;
	}

	public String update() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer hallid = Integer.parseInt(request.getParameter("hallid"));
		String hallname = request.getParameter("hallname");
		if (hallname == null || hallname == "") {
			Object[] param = { hallid };
			hall = hallManager.findHall("from Hall h where h.hallid=?", param);
			List<Cinema> cinemalist = cinemaManager.findAllList("from Cinema");
			request.setAttribute("cinemalist", cinemalist);
			request.setAttribute("hall", hall);
		} else {
			Hall h = null;
			Integer cinemaid = Integer.parseInt(request
					.getParameter("cinemaid"));
			Object[] param = { hallname, cinemaid };
			h = hallManager.findHall(
					"from Hall h where h.hallname=? and h.cinemaid=?", param);
			if (null == h) {
				Object[] param1 = { hallid };
				hall = hallManager.findHall("from Hall h where h.hallid=?",
						param1);
				hall.setHallname(hallname);
				hall.setCinemaid(Integer.parseInt(request
						.getParameter("cinemaid")));
				hallManager.updateHall(hall);
				request.setAttribute("tip", "修改成功");
				List<Cinema> cinemalist = cinemaManager
						.findAllList("from Cinema");
				request.setAttribute("cinemalist", cinemalist);
				request.setAttribute("hall", hall);
			} else {
				Object[] param1 = { hallid };
				hall = hallManager.findHall("from Hall h where h.hallid=?",
						param1);
				request.setAttribute("tip", "影厅已存在修改失败");
				List<Cinema> cinemalist = cinemaManager
						.findAllList("from Cinema");
				request.setAttribute("cinemalist", cinemalist);
				request.setAttribute("hall", hall);
			}

		}
		return SUCCESS;
	}

	public String delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		hall.setHallid(Integer.parseInt(request.getParameter("hallid")));
		hallManager.deleteHall(hall);
		return "ok";
	}

	public CinemaManager getCinemaManager() {
		return cinemaManager;
	}

	@Resource
	public void setCinemaManager(CinemaManager cinemaManager) {
		this.cinemaManager = cinemaManager;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public HallManager getHallManager() {
		return hallManager;
	}

	@Resource
	public void setHallManager(HallManager hallManager) {
		this.hallManager = hallManager;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

}
