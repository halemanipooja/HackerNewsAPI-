package com.paytminsider.test.entites;

public class CommentEntity {

	private String by;
	private String text;
	private int kids;
	private int age;
	
	public CommentEntity() {}
	
	public CommentEntity(String by, String text, int kids, int age) {
		super();
		this.by = by;
		this.text = text;
		this.kids = kids;
		this.age = age;
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
	public void setKids(int kids) {
		this.kids = kids;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "CommentEntity [by=" + by + ", text=" + text + ", kids=" + kids + ", age=" + age + "]";
	}
	
	
}
