package com.legato.webutil.model;

public class UserProfile {
	private String afId;
	private String username;
	private String fullname;
	private String password;
	private String role;
	public UserProfile() {
		super();
	}
	public UserProfile(String afId, String username, String fullname, String password, String role) {
		super();
		this.afId = afId;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.role = role;
	}
	public String getAfId() {
		return afId;
	}
	public void setAfId(String afId) {
		this.afId = afId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFirstname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserProfile [afId=" + afId + ", username=" + username + ", fullname=" + fullname + 
				", password=" + password + ", role=" + role + "]";
	}
}