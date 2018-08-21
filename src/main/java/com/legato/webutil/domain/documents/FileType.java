package com.legato.webutil.domain.documents;

public enum FileType {
	DOCUMENTS(0, "Documents"),
	WEBEX(1, "Webex Recording");
	
	public int id;
	public String name;
	
	private FileType() {
		
	}
	
	private FileType(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getId(){
		return this.id;
	}
	
	public FileType getFileType(){
		return this;
	}
}