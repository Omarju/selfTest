package com.omar.selftest.domain;

import java.io.Serializable;

public class User implements Serializable{
	private String id;
    private String userName;
    private String userSex;
    private Integer userAge;
    

    public User() {
		super();
	}

	public User(String id,String userName, String userSex, Integer userAge) {
    	super();
    	this.id = id;
        this.userName = userName;
        this.userSex = userSex;
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAge=" + userAge +
                '}';
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
}
