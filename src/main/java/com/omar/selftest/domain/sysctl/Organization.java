package com.omar.selftest.domain.sysctl;

import java.io.Serializable;

public class Organization implements Serializable{

	private String id;
    private String organizationName;
    private String organizationHeader;
    private String organizationParent;
    
    public Organization() {
		super();
	}
    
	public Organization(String id, String organizationName, String organizationHeader, String organizationParent) {
		super();
		this.id = id;
		this.organizationName = organizationName;
		this.organizationHeader = organizationHeader;
		this.organizationParent = organizationParent;
	}

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationHeader() {
		return organizationHeader;
	}
	public void setOrganizationHeader(String organizationHeader) {
		this.organizationHeader = organizationHeader;
	}
	public String getOrganizationParent() {
		return organizationParent;
	}
	public void setOrganizationParent(String organizationParent) {
		this.organizationParent = organizationParent;
	}
    
    
}
