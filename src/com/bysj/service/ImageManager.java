package com.bysj.service;

import java.util.List;

import com.bysj.model.Image;

public interface ImageManager {
	public void addImage(Image image) throws Exception;
	public void deleteImage(Image image) throws Exception;
	public void updateImage(Image image) throws Exception;
	public List<Image> findAllList();
	public List<Image> findImageByMoiveid(Integer moiveid);
	public Image findImage(String hql,Object[] param);
}
