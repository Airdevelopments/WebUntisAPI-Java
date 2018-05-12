package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;

public class Holiday implements Serializable{

	private static final long serialVersionUID = -3332334457215754900L; //will not change in any version of the API

	private int id;
	private String name;
	private String longName;
	private int startDate;
	private int endDate;
	
	public Holiday(int id, String name, String longName, int startDate, int endDate)
	{
		this.id = id;
		this.name = name;
		this.longName = longName;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
}
