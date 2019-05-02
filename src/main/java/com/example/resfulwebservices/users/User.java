package com.example.resfulwebservices.users;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the user")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Size(min = 2, message = "Name should have atleast two characters" )
	@ApiModelProperty(notes = "Name should have atleast two character")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "Birth date should be in the past")
	private Date birthDate; 
//	private List<UserPost> posts;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
//	public List<UserPost> getPosts() {
//		return posts;
//	}
//	public void setPosts(List<UserPost> posts) {
//		this.posts = posts;
//	}
	public User(int id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public User() {
		super();
	}
//	public User(int id, String name, Date birthDate, List<UserPost> posts) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.birthDate = birthDate;
//		this.posts = posts;
//	}
	
}
