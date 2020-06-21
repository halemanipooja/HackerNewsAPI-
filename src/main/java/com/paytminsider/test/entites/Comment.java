package com.paytminsider.test.entites;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"descendants", "id", "type", "deleted", "dead", "parent", "poll", "parts", "time", "score", "url", "title"})
@Component
public class Comment {

	private String by;
	private String text;
	private int kids;
	
	public Comment() {}

	public Comment(String by, String text, int kids) {
		super();
		this.by = by;
		this.text = text;
		this.kids = kids;
	}
	
	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getKids() {
		return kids;
	}

	public void setKids(List<Integer> kids) {
		this.kids = kids.size();
	}

	
	@Override
	public String toString() {
		return "Comment [by=" + by + ", text=" + text + "]";
	}
}
