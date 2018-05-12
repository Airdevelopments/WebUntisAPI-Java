package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;

public class Subject implements Serializable{

	private static final long serialVersionUID = -3611563529962928049L; //will not change in any version of the API
	
	private int id;
	private String name;
	private String longName;
	private String alternateName;
	private boolean active;
	
	public Subject(int id, String name, String longName, String alternateName, boolean active)
	{
		this.id = id;
		this.name = name;
		this.longName = longName;
		this.alternateName = alternateName;
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

	public String getAlternateName() {
		return alternateName;
	}

	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
