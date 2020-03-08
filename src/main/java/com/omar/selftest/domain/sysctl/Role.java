package com.omar.selftest.domain.sysctl;

import java.io.Serializable;

public class Role implements Serializable{
	
	private String id;
	private String roleName;
	
	public Role() {
		super();
	}
	
	public Role(String id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return roleName;
	}
	public void setName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
