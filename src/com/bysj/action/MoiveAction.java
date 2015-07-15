package com.bysj.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bysj.model.Image;
import com.bysj.model.Moive;
import com.bysj.service.ImageManager;
import com.bysj.service.MoiveManager;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component("moiveAction")
@Scope("prototype")
public class MoiveAction extends ActionSupport implements ModelDriven<Moive>{
    
	private static final long serialVersionUID = 1L;
	private MoiveManager moiveManager;
    private Moive moive=new Moive();
    private ImageManager imageManager;
    private File upload; // 代表上传的文件本身 ---> 图片A本身 图片B本身
	private String uploadContentType; // 代表文件类型 ---> png jpg
	private String uploadFileName; // 代表文件名 ---> A.PNG B.jpg
    
    
    public String info(){
    	 HttpServletRequest request = ServletActionContext.getRequest();
    	 int id=Integer.parseInt(request.getParameter("moiveid"));
    	 Moive m=new Moive();
    	 m=moiveManager.findMoiveById(id);
    	 request.setAttribute("moive", m);
    	return "test";
    }
    
    public void GetFilmData() throws Exception{
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
    	String id =request.getParameter("moiveid");
    	Integer moiveid=Integer.parseInt(id);
    	List<Image> l=imageManager.findImageByMoiveid(moiveid);
    	Gson gs=new Gson();
    	String jsonArray=gs.toJson(l);
    	PrintWriter out =response.getWriter();
    	out.print(jsonArray);
    	out.flush();
		out.close();
    	System.out.println(jsonArray);
    	
    }
    
    public String manage(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	List<Moive> moivelist =moiveManager.findAllList();
    	request.setAttribute("moivelist", moivelist);
    	return SUCCESS;
    }
    
    public String add() throws Exception{
    	HttpServletRequest request = ServletActionContext.getRequest();
    	if(request.getParameter("moivename")==null || request.getParameter("moivename")==""){
    		
    	}else{
    		moive.setMoivename(request.getParameter("moivename"));
        	moive.setReleasetime(Date.valueOf(request.getParameter("releasetime")));
        	moive.setDirector(request.getParameter("director"));
        	moive.setStarring(request.getParameter("starring"));
        	moive.setType(request.getParameter("type"));
        	moive.setNation(request.getParameter("nation"));
        	moive.setVersion(request.getParameter("version"));
        	moive.setTotaltime(request.getParameter("totaltime"));
        	moive.setMoiveplot(request.getParameter("moiveplot"));
        	moive.setLanguage(request.getParameter("language"));
        	moive.setScore(request.getParameter("score"));
        	Object[] param={moive.getMoivename(),moive.getReleasetime(),moive.getDirector()};
        	Moive m=null;
        	m=moiveManager.findMoive("from Moive m where m.moivename=? and m.releasetime=? and m.director=?", param);
        	if(null==m){
        		String realpath = ServletActionContext.getServletContext().getRealPath("/Upload/FilmPic/Temp");
                //例如D:\apache-tomcat-6.0.18\webapps\struts2_upload\images
                if (upload != null) {
                    File savefile = new File(new File(realpath), uploadFileName);
                    if (!savefile.getParentFile().exists())
                        savefile.getParentFile().mkdirs();
                    FileUtils.copyFile(upload, savefile);
                    String path="/Upload/FilmPic/Temp/"+ uploadFileName  ;
                    moive.setMoiveimage(path);
                    moiveManager.addMoive(moive);
            		request.setAttribute("tip", "添加成功");
                }else{
                	request.setAttribute("tip", "影片添加失败");
                }
        		
        	}else{
        		request.setAttribute("tip", "影片已存在添加失败");
        	}
    	}
    	
    	return SUCCESS;
    }
    
    public String update() throws Exception{
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String moivename=request.getParameter("moivename");
    	Integer moiveid=Integer.parseInt(request.getParameter("moiveid"));
    	if(moivename==null || moivename==""){
			Object[] param={moiveid};
			moive=moiveManager.findMoiveById(moiveid);
			request.setAttribute("m", moive);
    	}else{
    			moive=moiveManager.findMoiveById(moiveid);
    			moive.setMoivename(moivename);
            	moive.setReleasetime(Date.valueOf(request.getParameter("releasetime")));
            	moive.setDirector(request.getParameter("director"));
            	moive.setStarring(request.getParameter("starring"));
            	moive.setType(request.getParameter("type"));
            	moive.setNation(request.getParameter("nation"));
            	moive.setVersion(request.getParameter("version"));
            	moive.setTotaltime(request.getParameter("totaltime"));
            	moive.setMoiveplot(request.getParameter("moiveplot"));
            	moive.setLanguage(request.getParameter("language"));
            	moive.setScore(request.getParameter("score"));
            	if (upload != null) {
            		String realpath = ServletActionContext.getServletContext().getRealPath("/Upload/FilmPic/Temp");
                    File savefile = new File(new File(realpath), uploadFileName);
                    if (!savefile.getParentFile().exists())
                        savefile.getParentFile().mkdirs();
                    FileUtils.copyFile(upload, savefile);
                    String path="/Upload/FilmPic/Temp/"+ uploadFileName  ;
                    moive.setMoiveimage(path);
                    moiveManager.updateMoive(moive);
                    request.setAttribute("m", moive);
            		request.setAttribute("tip", "修改成功");
                }else{
                	moiveManager.updateMoive(moive);
                	request.setAttribute("m", moive);
                	request.setAttribute("tip", "修改成功");
                }
    		}
    	return SUCCESS;
    }

    public String delete() throws Exception{
		 HttpServletRequest request = ServletActionContext.getRequest();
		 moive.setMoiveid(Integer.parseInt(request.getParameter("moiveid")));
		 moiveManager.deleteMoive(moive);
		 return "ok";
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

	public ImageManager getImageManager() {
		return imageManager;
	}
	@Resource
	public void setImageManager(ImageManager imageManager) {
		this.imageManager = imageManager;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	

}
