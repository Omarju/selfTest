package com.omar.selftest.domain;

import java.io.Serializable;

public class File implements Serializable{
	private String id;
    private String fileOriginalName;
    private String fileStorageName;
    private String filePath;
    private String tags;

    public File() {
		super();
	}

	public File(String id,String fileOriginalName, String fileStorageName,String filePath,String tags) {
    	super();
    	this.id = id;
        this.fileOriginalName = fileOriginalName;
        this.fileStorageName = fileStorageName;
        this.filePath = filePath;
        this.tags = tags;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileOriginalName() {
		return fileOriginalName;
	}

	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}

	public String getFileStorageName() {
		return fileStorageName;
	}

	public void setFileStorageName(String fileStorageName) {
		this.fileStorageName = fileStorageName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
}
