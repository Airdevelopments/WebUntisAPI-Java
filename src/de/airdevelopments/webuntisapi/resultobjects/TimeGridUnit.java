package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;
import java.util.ArrayList;

public class TimeGridUnit implements Serializable{

	private static final long serialVersionUID = 463065813959514803L; //will not change in any version of the API

	private int day;
	private ArrayList<TimeUnit> timeUnits;
	
	public TimeGridUnit(int day, ArrayList<TimeUnit> timeUnits)
	{
		this.day = day;
		this.timeUnits = timeUnits;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public ArrayList<TimeUnit> getTimeUnits() {
		return timeUnits;
	}

	public void setTimeUnits(ArrayList<TimeUnit> timeUnits) {
		this.timeUnits = timeUnits;
	}
}
