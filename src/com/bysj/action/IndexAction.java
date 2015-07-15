package com.bysj.action;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bysj.model.Moive;
import com.bysj.service.MoiveManager;
import com.bysj.vo.MoiveIndex;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Component("IndexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport implements ModelDriven<Moive>{
	private MoiveManager moiveManager;
    private Moive moive;
    public String I(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpSession session=request.getSession();
    	Date date = new Date(); 
    	Object[] param = new Object[] { date };
    	List<MoiveIndex> l1=moiveManager.findListByDate("select new com.bysj.vo.MoiveIndex(m.moiveid,m.moivename,m.releasetime,m.score,i.imageadr) from Moive m,Image i where m.releasetime<? and i.imagename='FilmPic' and m.moiveid=i.moiveid", param);    	
    	List<MoiveIndex> l2=moiveManager.findListByDate("select new com.bysj.vo.MoiveIndex(m.moiveid,m.moivename,m.releasetime,m.score,i.imageadr) from Moive m,Image i where m.releasetime>? and i.imagename='FilmPic' and m.moiveid=i.moiveid", param);
    	String view=(String) session.getAttribute("view");
    	String view1=(String) session.getAttribute("view1");
    	if(view==null && view1==null){
    		session.setAttribute("view1", "display:none");
    	}
    	request.setAttribute("l1", l1);
    	request.setAttribute("l2", l2);
		return "index";
    	
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

	@Override
	public Moive getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
