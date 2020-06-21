package com.paytminsider.hackernews.entites;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@JsonIgnoreProperties(value = {"descendants", "id", "text", "deleted", "dead", "parent", "poll", "parts"})
public class Story {

	private String title;
	private String url;
	private int score;
	private String time;
	private String by;
	private String type;
	private List<Integer> kids;
	
	public Story(String title, String url, int score, String time, String by) {
		this.title = title;
		this.url = url;
		this.score = score;
		this.time = time;
		this.by = by;
	}
	
	
	public Story() {
		
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public String getTitle() {
		return title;
	}
	public List<Integer> getKids() {
		return kids;
	}


	public void setKids(List<Integer> kids) {
		this.kids = kids;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	
	
	@Override
	public String toString() {
		return "Story [title=" + title + ", url=" + url + ", score=" + score + ", time=" + time + ", by=" + by
				+ ", type=" + type + ", kids=" + kids + "]";
	}
	
	
}
