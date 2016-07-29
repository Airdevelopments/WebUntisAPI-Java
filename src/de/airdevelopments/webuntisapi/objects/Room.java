package de.airdevelopments.webuntisapi.objects;

import java.io.Serializable;

public class Room implements Serializable{
	
	public Integer id;
	public String name;
	public String longname;
	
	public Room(Integer id, String name, String longname)
	{
		this.id = id;
		this.name = name;
		this.longname = longname;
	}

}
