package com.bysj.action;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bysj.model.Orders;
import com.bysj.model.User;
import com.bysj.service.OrderManager;
import com.bysj.service.ScheduleManager;
import com.bysj.service.UserManager;
import com.bysj.vo.MoiveInfo2;
import com.bysj.vo.OrderInfo;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Component("orderAction")
@Scope("prototype")
public class OrderAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private OrderManager ordermanager;
	private ScheduleManager scheduleManager;
	private UserManager userManage;
	private Orders order = new Orders();

	public void CreateSeatTicket() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String code = request.getParameter("verifyCode").toLowerCase();
		String authCode = session.getAttribute("authCode").toString()
				.toLowerCase();
		if (!code.equals(authCode)) {
			String jsonArray = "{\"errMsg\":\"创建订单提交验证码错误！\"}";
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} else {
			Integer scheduleid = Integer
					.parseInt(request.getParameter("seqNo"));
			Integer cinemaid = Integer.parseInt(request
					.getParameter("cinemaNo"));
			String seatName = request.getParameter("seatName");
			Long phone = Long.parseLong(request.getParameter("mobileNo"));
			Integer price = Integer.parseInt(request.getParameter("sealPrice"));
			order.setScheduleid(scheduleid);
			order.setUserid(userManage.findUserByPhone(phone).getUserid());
			order.setPaysatus(0);
			Object[] param = new Object[] { scheduleid };
			List<MoiveInfo2> l = scheduleManager
					.findScheduleBy(
							"select new com.bysj.vo.MoiveInfo2(m.moiveid,m.moivename,i.imageadr,m.totaltime,s.showdate,s.showtime,s.versionid,m.language,c.cinemaid,c.cinemaname,h.hallname,s.price) from Schedule s,Moive m,Hall h,Cinema c,Image i where s.scheduleid=? and s.moiveid=m.moiveid and m.moiveid=i.moiveid and s.hallid=h.hallid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' ",
							param);
			String leix = "";
			switch (l.get(0).getShowType()) {
			case 0:
				leix = "2D";
			case 1:
				leix = "3D";
			case 3:
				leix = "IMAX2D";
			case 4:
				leix = "4D";
			case 5:
				leix = "IMAX3D";
			case 6:
				leix = "DMAX2D";
			case 7:
				leix = "DMAX3D";
			}
			String details = "影片：" + l.get(0).getCFilmName() + "<br/>影院："
					+ l.get(0).getCinemaName() + "<br/>影厅："
					+ l.get(0).getHallName() + "<br/>类型：" + leix + "<br/>场次："
					+ l.get(0).getShowDate() + " " + l.get(0).getShowTime()
					+ "<br/>座位：";
			String[] s2 = seatName.split("\\|");
			for (int i = 0; i < s2.length; i++) {
				String[] s3 = s2[i].split("_");
				details += s3[0] + "区" + s3[1] + "排" + s3[2] + "座<br/>";
			}
			order.setOrderdetails(details);
			order.setPrice(price * s2.length);
			ordermanager.addOrder(order);
			String jsonArray = "{\"errMsg\":\"" + order.getOrderid()
					+ "&seatName=" + seatName + "\",\"errorCode\":\"0\"}";
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		}
	}

	public String ShowSeatTicket() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer orderid = Integer.parseInt(request.getParameter("orderno"));
		Object[] param = new Object[] { orderid };
		List<OrderInfo> l = ordermanager
				.findOrder(
						"select new com.bysj.vo.OrderInfo(i.imageadr,m.moivename,m.language,v.versionname,m.totaltime,c.cinemaname,s.showdate,s.tip,s.showtime,u.phone,s.price) from Orders o,Schedule s,Moive m,User u,Image i,Version v,Hall h,Cinema c where o.orderid=? and o.scheduleid=s.scheduleid and o.userid=u.userid and s.moiveid=m.moiveid and s.hallid=h.hallid and s.versionid=v.versionid and m.moiveid=i.moiveid and h.cinemaid=c.cinemaid and i.imagename='FilmPic' ",
						param);
		OrderInfo oi = new OrderInfo();
		oi = l.get(0);
		String seatName = request.getParameter("seatName");
		String[] s2 = seatName.split("\\|");
		Integer num = s2.length;
		String details = "";
		for (int i = 0; i < s2.length; i++) {
			String[] s3 = s2[i].split("_");
			details += s3[0] + "区" + s3[1] + "排" + s3[2] + "座<br/>";
		}
		request.setAttribute("OrderInfo", oi);
		request.setAttribute("seatnum", num);
		request.setAttribute("seatname", details);
		return "ticket";
	}

	public String manage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Orders> orderlist = ordermanager.findAllList("from Orders");
		List<User> userlist = userManage
				.findAll("select new User(u.userid,u.phone) from User u");
		request.setAttribute("orderlist", orderlist);
		request.setAttribute("userlist", userlist);
		return SUCCESS;
	}

	public OrderManager getOrdermanager() {
		return ordermanager;
	}

	@Resource
	public void setOrdermanager(OrderManager ordermanager) {
		this.ordermanager = ordermanager;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrders(Orders order) {
		this.order = order;
	}

	public ScheduleManager getScheduleManager() {
		return scheduleManager;
	}

	@Resource
	public void setScheduleManager(ScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}

	public UserManager getUserManage() {
		return userManage;
	}

	@Resource
	public void setUserManage(UserManager userManage) {
		this.userManage = userManage;
	}

}
