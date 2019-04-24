package com.example.resfulwebservices.users;

public class UserPost {
	private int id;
	private String comment;
	public UserPost(int id, String comment) {
		super();
		this.id = id;
		this.comment = comment;
	}
	public UserPost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getComment() {
		return comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "UserPost [id=" + id + ", comment=" + comment + "]";
	}
}
