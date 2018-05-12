package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;

public class Department implements Serializable{

	private static final long serialVersionUID = -619723686611430371L; //will not change in any version of the API
	
	private int id;
	private String name;
	private String longName;
	
	public Department(int id, String name, String longName)
	{
		this.id = id;
		this.name = name;
		this.longName = longName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
