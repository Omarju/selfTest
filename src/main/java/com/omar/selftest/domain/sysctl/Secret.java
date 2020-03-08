package com.omar.selftest.domain.sysctl;

import java.io.Serializable;

public class Secret implements Serializable{

	private String id;
	private String roleID;
	private String OrganizationID;
	private Integer secret;
	
	public Secret() {
		super();
	}
	
	public Secret(String id, String roleID, String organizationID, Integer secret) {
		super();
		this.id = id;
		this.roleID = roleID;
		OrganizationID = organizationID;
		this.secret = secret;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getOrganizationID() {
		return OrganizationID;
	}

	public void setOrganizationID(String organizationID) {
		OrganizationID = organizationID;
	}

	public Integer getSecret() {
		return secret;
	}

	public void setSecret(Integer secret) {
		this.secret = secret;
	}
	
	
}
