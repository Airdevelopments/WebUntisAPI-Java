package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;

public class Room implements Serializable{

	private static final long serialVersionUID = 5211468946611339440L; //will not change in any version of the API
	
	private int id;
	private String name;
	private String longName;
	private boolean active;
	private String building;
	
	public Room(int id, String name, String longName, boolean active, String building)
	{
		this.id = id;
		this.name = name;
		this.longName = longName;
		this.active = active;
		this.building = building;
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

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}
}
