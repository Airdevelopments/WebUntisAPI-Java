package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;

public class TimeUnit implements Serializable{

	private static final long serialVersionUID = 8625716601190189300L; //will not change in any version of the API
	
	private String name;
	private int startTime;
	private int endTime;
	
	public TimeUnit(String name, int startTime, int endTime)
	{
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	
}
