package com.bysj.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bysj.model.Cinema;
import com.bysj.model.City;
import com.bysj.service.CinemaManager;
import com.bysj.service.CityManager;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Component("cinemaAction")
@Scope("prototype")
public class CinemaAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private CinemaManager cinemaManager;
	private CityManager citymanager;
	private Cinema cinema = new Cinema();

	public String manage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Cinema> cinemalist = cinemaManager.findAllList("from Cinema");
		request.setAttribute("cinemalist", cinemalist);
		return SUCCESS;
	}

	public String add() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cinemaname = request.getParameter("cinemaname");
		if (cinemaname == null || cinemaname == "") {
			List<City> citylist = citymanager.findAllList("from City");
			request.setAttribute("citylist", citylist);
		} else {
			Cinema c = null;
			Object[] param = { cinemaname };
			c = cinemaManager.findCinema("from Cinema c where c.cinemaname=?",
					param);
			if (null == c) {
				cinema.setCinemaname(cinemaname);
				cinema.setCityid(Integer.parseInt(request
						.getParameter("cityid")));
				cinema.setAddress(request.getParameter("address"));
				cinema.setTelephone(request.getParameter("telephone"));
				cinema.setRoutes(request.getParameter("routes"));
				cinema.setPreferentialinfo(request
						.getParameter("preferentialinfo"));
				cinemaManager.addCinema(cinema);
				request.setAttribute("tip", "添加成功");
				List<City> citylist = citymanager.findAllList("from City");
				request.setAttribute("citylist", citylist);
			} else {
				request.setAttribute("tip", "影城已存在添加失败");
				List<City> citylist = citymanager.findAllList("from City");
				request.setAttribute("citylist", citylist);
			}

		}
		return SUCCESS;
	}

	public String update() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cinemaname = request.getParameter("cinemaname");
		Integer cinemaid = Integer.parseInt(request.getParameter("cinemaid"));
		if (cinemaname == null || cinemaname == "") {
			Object[] param = { cinemaid };
			cinema = cinemaManager.findCinema(
					"from Cinema c where c.cinemaid=?", param);
			List<City> citylist = citymanager.findAllList("from City");
			request.setAttribute("citylist", citylist);
			request.setAttribute("cinema", cinema);
		} else {
			Cinema c = null;
			Integer cityid = Integer.parseInt(request.getParameter("cityid"));
			Object[] param = { cinemaname, cityid };
			c = cinemaManager.findCinema(
					"from Cinema c where c.cinemaname=? and c.cityid=?", param);
			if (null == c) {
				cinema.setCinemaid(cinemaid);
				cinema.setCinemaname(cinemaname);
				cinema.setCityid(cityid);
				cinema.setAddress(request.getParameter("address"));
				cinema.setTelephone(request.getParameter("telephone"));
				cinema.setRoutes(request.getParameter("routes"));
				cinema.setPreferentialinfo(request
						.getParameter("preferentialinfo"));
				cinemaManager.updateCinema(cinema);
				request.setAttribute("tip", "修改成功");
				List<City> citylist = citymanager.findAllList("from City");
				request.setAttribute("citylist", citylist);
				request.setAttribute("cinema", cinema);
			} else {
				Object[] param1 = { cinemaid };
				cinema = cinemaManager.findCinema(
						"from Cinema c where c.cinemaid=?", param1);
				List<City> citylist = citymanager.findAllList("from City");
				request.setAttribute("citylist", citylist);
				request.setAttribute("cinema", cinema);
				request.setAttribute("tip", "影城已存在修改失败");
			}

		}
		return SUCCESS;
	}

	public String delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		cinema.setCinemaid(Integer.parseInt(request.getParameter("cinemaid")));
		cinemaManager.deleteCinema(cinema);
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

	public CityManager getCitymanager() {
		return citymanager;
	}

	@Resource
	public void setCitymanager(CityManager citymanager) {
		this.citymanager = citymanager;
	}

}
