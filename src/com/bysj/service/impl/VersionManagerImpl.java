package com.bysj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bysj.dao.VersionDao;
import com.bysj.model.Version;
import com.bysj.service.VersionManager;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Component("versionManager")
public class VersionManagerImpl implements VersionManager {
	private VersionDao<Version> versionDao;

	@Override
	public List<Version> findAllList() {
		return versionDao.find("from Version");
	}

	public VersionDao<Version> getVersionDao() {
		return versionDao;
	}

	@Resource
	public void setVersionDao(VersionDao<Version> versionDao) {
		this.versionDao = versionDao;
	}

}
