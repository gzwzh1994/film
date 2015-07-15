package com.bysj.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bysj.model.Image;
import com.bysj.model.Moive;
import com.bysj.service.ImageManager;
import com.bysj.service.MoiveManager;
import com.opensymphony.xwork2.ActionSupport;

@Component("imageAction")
@Scope("prototype")
public class ImageAction extends ActionSupport{

	private ImageManager imageManager;
	private Image image=new Image();
	private MoiveManager moiveManager;
    private Moive moive=new Moive();
	private File upload; // 代表上传的文件本身 ---> 图片A本身 图片B本身
	private String uploadContentType; // 代表文件类型 ---> png jpg
	private String uploadFileName; // 代表文件名 ---> A.PNG B.jpg
	
	public String manage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Image> imagelist=imageManager.findAllList();
		request.setAttribute("imagelist", imagelist);
		List<Moive> moivelist=moiveManager.findAllList();
		request.setAttribute("moivelist", moivelist);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String imagename=request.getParameter("imagename");
		if(imagename==null || imagename==""){
			List<Moive> moivelist=moiveManager.findAllList();
			request.setAttribute("moivelist", moivelist);
		}else{
			Image i=null;
			if (upload != null) {
				String realpath = ServletActionContext.getServletContext().getRealPath("/Upload/FilmPicture/Temp");
				String path="/Upload/FilmPicture/Temp/"+ uploadFileName  ;
				Object[] param={path};
				i=imageManager.findImage("from Image i where i.imageadr=?", param);
				if(null==i){
					File savefile = new File(new File(realpath), uploadFileName);
	                if (!savefile.getParentFile().exists())
	                    savefile.getParentFile().mkdirs();
	                FileUtils.copyFile(upload, savefile);
	                image.setImagename(imagename);
	                image.setMoiveid(Integer.parseInt(request.getParameter("moiveid")));
	                image.setImageadr(path);
	                imageManager.addImage(image);
	                request.setAttribute("tip", "添加成功");
				}else{
					request.setAttribute("tip", "图片已存在添加失败");
				}
            }else{
            	request.setAttribute("tip", "图片未上传添加失败");
            }
		}
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		image.setImageid(Integer.parseInt(request.getParameter("imageid")));
		imageManager.deleteImage(image);
		return "ok";
	}
	
	public ImageManager getImageManager() {
		return imageManager;
	}
	@Resource
	public void setImageManager(ImageManager imageManager) {
		this.imageManager = imageManager;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
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
