package de.airdevelopments.webuntisapi.objects;

import java.io.Serializable;

public class Student implements Serializable{
	
	public String name;
	public String forename;
	public String longname;
	public Gender gender;
	
	public Student(String name, String forename, String longname, Gender gender)
	{
		this.name = name;
		this.forename = forename;
		this.longname = longname;
		this.gender = gender;
	}

}
