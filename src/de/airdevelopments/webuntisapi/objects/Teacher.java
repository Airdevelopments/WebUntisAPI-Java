package de.airdevelopments.webuntisapi.objects;

import java.io.Serializable;

public class Teacher implements Serializable{
	
	public String name;
	public String forename;
	public String longname;
	
	public Teacher(String name, String forename, String longname)
	{
		this.name = name;
		this.forename = forename;
		this.longname = longname;
	}

}
