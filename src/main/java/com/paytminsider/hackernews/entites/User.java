package com.paytminsider.hackernews.entites;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"submitted", "about"})
@Component
public class User {
	
	private String created;
	private String id;
	private String karma;
	
	public User() {}
	
	public User(String created, String id, String karma) {
		super();
		this.created = created;
		this.id = id;
		this.karma = karma;
	}
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKarma() {
		return karma;
	}
	public void setKarma(String karma) {
		this.karma = karma;
	}
	
}
