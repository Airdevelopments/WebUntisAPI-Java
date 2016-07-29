package de.airdevelopments.webuntisapi.objects;

import java.io.Serializable;

public class Substitution implements Serializable{
	
	public SubstitutionType type;
	public Integer date;
	public String classname;
	public Integer schoolhour;
	public String teacher;
	public String subject;
	public String room;
	public String text;
	
	public Substitution(SubstitutionType type, Integer date, String classname, Integer starttime, String teacher, String subject, String room, String text)
	{
		this.type = type;
		this.date = date;
		this.classname = classname;
		this.schoolhour = convertToHour(starttime);
		this.teacher = teacher;
		this.subject = subject;
		this.room = room;
		this.text = text;
	}
	
	@Deprecated
	private Integer convertToHour(Integer starttime)
	{
		if(starttime == 742)
		{
			return 1;
		}
		else if(starttime == 835)
		{
			return 2;
		}
		else if(starttime == 940)
		{
			return 3;
		}
		else if(starttime == 1025)
		{
			return 4;
		}
		else if(starttime == 1125)
		{
			return 5;
		}
		else if(starttime == 1213)
		{
			return 6;
		}
		else if(starttime == 1350)
		{
			return 7;
		}
		else if(starttime == 1435)
		{
			return 8;
		}
		else if(starttime == 1530)
		{
			return 9;
		}
		return 0;
	}
	
}
