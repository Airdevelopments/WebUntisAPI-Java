package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;

public class Student implements Serializable{
	
	private static final long serialVersionUID = -1228120600821745769L; //will not change in any version of the API
	
	private int id;
	private String name;
	private String foreName;
	private String longName;
	private Gender gender;
	
	public Student(int id, String name, String foreName, String longName, Gender gender)
	{
		this.id = id;
		this.name = name;
		this.foreName = foreName;
		this.longName = longName;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getForeName() {
		return foreName;
	}

	public void setForeName(String forename) {
		this.foreName = forename;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longname) {
		this.longName = longname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
