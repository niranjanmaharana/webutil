package com.legato.webutil.domain.documents;

import java.util.Date;

public class Document {
	private boolean directory;
	private String fileName;
	private String filePath;
	private Date creationTime;
	private Date lastAccessTime;
	private Date lastModifiedTime;
	
	public Document() {}
	
	public Document(boolean directory, String fileName, String filePath, Date creationTime, Date lastAccessTime, Date lastModifiedTime) {
		super();
		this.directory = directory;
		this.fileName = fileName;
		this.filePath = filePath;
		this.creationTime = creationTime;
		this.lastAccessTime = lastAccessTime;
		this.lastModifiedTime = lastModifiedTime;
	}

	public boolean isDirectory() {
		return directory;
	}
	public void setDirectory(boolean directory) {
		this.directory = directory;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
}