package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;

public class BaseClass implements Serializable{
	
	private static final long serialVersionUID = -9130569950252942441L; //will not change in any version of the API
	
	private int id;
	private String name;
	private String longName;
	private boolean active;
	
	public BaseClass(int id, String name, String longName, boolean active)
	{
		this.id = id;
		this.name = name;
		this.longName = longName;
		this.active = active;
	}

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

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longname) {
		this.longName = longname;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
