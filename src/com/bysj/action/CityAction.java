package com.bysj.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bysj.model.City;
import com.bysj.service.CityManager;
import com.bysj.vo.CityInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Component("cityAction")
@Scope("prototype")
public class CityAction extends ActionSupport implements ModelDriven<CityInfo> {

	private static final long serialVersionUID = 1L;
	private CityManager citymanager;
	private City city = new City();
	private CityInfo cityInfo = new CityInfo();

	public String manage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<City> citylist = citymanager.findAllList("from City");
		request.setAttribute("citylist", citylist);
		return SUCCESS;
	}

	public String add() throws Exception {
		City c = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		city.setCityid(Integer.parseInt(cityInfo.getCityid()));
		city.setCityname(cityInfo.getCityname());
		Object[] param = { Integer.parseInt(cityInfo.getCityid()) };
		c = citymanager.findCity("from City c where c.cityid=?", param);
		if (null == c) {
			citymanager.addCity(city);
			request.setAttribute("tip", "添加成功");
		} else {
			request.setAttribute("tip", "城市已存在添加失败");
		}
		return SUCCESS;

	}

	public String update() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer cityid = Integer.parseInt(request.getParameter("cityid"));
		String cityname = request.getParameter("cityname");
		city.setCityid(cityid);
		city.setCityname(cityname);
		request.setAttribute("city", city);
		return SUCCESS;
	}

	public String update2() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		City c = null;
		String cityname = cityInfo.getCityname();
		Object[] param = { cityname };
		c = citymanager.findCity("from City c where c.cityname=?", param);
		if (null == c) {
			city.setCityid(Integer.parseInt(cityInfo.getCityid()));
			city.setCityname(cityname);
			citymanager.updateCity(city);
			request.setAttribute("tip", "修改成功");
			request.setAttribute("city", city);
		} else {
			city = citymanager
					.findCity("from City c where c.cityname=?", param);
			request.setAttribute("tip", "城市已存在修改 失败");
			request.setAttribute("city", city);
		}
		return "success1";
	}

	public String delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		city.setCityid(Integer.parseInt(cityInfo.getCityid()));
		citymanager.deleteCity(city);
		return "ok";
	}

	public CityManager getCitymanager() {
		return citymanager;
	}

	@Resource
	public void setCitymanager(CityManager citymanager) {
		this.citymanager = citymanager;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public CityInfo getModel() {
		return cityInfo;
	}

}
