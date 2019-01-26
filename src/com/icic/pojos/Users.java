package com.icic.pojos;

public class Users {
	private int uid;
	private String username;
	private String password;
	private String name;
	private String addrees;
	private String mobile;
	private String email;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddrees() {
		return addrees;
	}
	public void setAddrees(String addrees) {
		this.addrees = addrees;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", addrees=" + addrees + ", mobile=" + mobile + ", email=" + email + "]";
	}
	public Users(String username, String password, String name, String addrees, String mobile, String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.addrees = addrees;
		this.mobile = mobile;
		this.email = email;
	}
	public Users() {
		super();
	}
	
	
}
