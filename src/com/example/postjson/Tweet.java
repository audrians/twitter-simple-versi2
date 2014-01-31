package com.example.postjson;

public class Tweet {
	private String created_at;
	private String user;
	private String text;
	
	Tweet(String created_at, String user, String text){
		this.created_at = created_at;
		this.user = user;
		this.text = text;
	}
	
	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}