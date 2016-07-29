package de.airdevelopments.webuntisapi.objects;

import java.io.Serializable;

public class Subject implements Serializable{
	
	public String name;
	public String longname;
	
	public Subject(String name, String longname)
	{
		this.name = name;
		this.longname = longname;
	}

}
