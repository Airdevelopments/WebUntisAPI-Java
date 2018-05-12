package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;

public class Teacher implements Serializable{

	private static final long serialVersionUID = 6704225637684231235L; //will not change in any version of the API
	
	private int id;
	private String name;
	private String foreName;
	private String longName;
	private String title;
	private boolean active;

	public Teacher(int id, String name, String foreName, String longName, String title, boolean active)
	{
		this.id = id;
		this.name = name;
		this.foreName = foreName;
		this.longName = longName;
		this.title = title;
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

	public String getForeName() {
		return foreName;
	}

	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
