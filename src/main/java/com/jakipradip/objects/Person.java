package com.jakipradip.objects;

import java.io.Serializable;

public class Person implements Serializable{
	
	private static final long serialVersionUID = -4864511291538848985L;
	private String fName;
	private String lName;
	
	public Person(){}
	
	public Person(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}

	@Override
	public String toString() {
		return "Person [fName=" + fName + ", lName=" + lName + "]";
	}
	
	
}
