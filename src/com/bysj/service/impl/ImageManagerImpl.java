package com.bysj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bysj.dao.ImageDao;
import com.bysj.model.Image;
import com.bysj.service.ImageManager;

@Component("imageManager")
public class ImageManagerImpl implements ImageManager {
	private ImageDao<Image> imagedao;

	@Override
	public void addImage(Image image) throws Exception {
		imagedao.save(image);
	}

	@Override
	public void deleteImage(Image image) throws Exception {
		imagedao.delete(image);
	}

	@Override
	public void updateImage(Image image) throws Exception {
		imagedao.saveOrUpdate(image);
	}

	@Override
	public List<Image> findAllList() {
		return imagedao.find("from Image i");
	}

	@Override
	public List<Image> findImageByMoiveid(Integer moiveid) {
		// TODO Auto-generated method stub
		return imagedao.find("from Image i where i.moiveid=? and i.imagename='FilmPicture' ", new Object[] { moiveid });
	}
	
	@Override
	public Image findImage(String hql, Object[] param) {
		return imagedao.get(hql, param);
	}

	public ImageDao<Image> getImagedao() {
		return imagedao;
	}
	@Resource
	public void setImagedao(ImageDao<Image> imagedao) {
		this.imagedao = imagedao;
	}
	
}
