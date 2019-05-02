package com.example.resfulwebservices.versioning;

public class PersonV2 {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonV2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonV2(String name) {
		super();
		this.name = name;
	}
}
