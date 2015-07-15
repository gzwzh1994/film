package com.bysj.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Version {
	private Integer versionid; // 版本编号
	private String versionname;// 版本名
	@Id
	public Integer getVersionid() {
		return versionid;
	}
	public void setVersionid(Integer versionid) {
		this.versionid = versionid;
	}
	public String getVersionname() {
		return versionname;
	}
	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}

	
	

}
