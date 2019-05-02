package com.example.resfulwebservices.versioning;

public class PersonV1 {
	private Name name;

	public PersonV1(Name name) {
		super();
		this.name = name;
	}
	public PersonV1() {
		super();
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
}
