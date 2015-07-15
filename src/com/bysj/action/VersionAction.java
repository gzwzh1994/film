package com.bysj.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bysj.model.Version;
import com.bysj.service.VersionManager;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author gzwzh1994
 * 
 */
@Component("versionAction")
@Scope("prototype")
public class VersionAction extends ActionSupport {
	private VersionManager versionManager;
	private Version version = new Version();

	public String manage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Version> versionlist = versionManager.findAllList();
		request.setAttribute("versionlist", versionlist);
		return SUCCESS;
	}

	public VersionManager getVersionManager() {
		return versionManager;
	}

	@Resource
	public void setVersionManager(VersionManager versionManager) {
		this.versionManager = versionManager;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

}
