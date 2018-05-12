package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;

public class Substitution implements Serializable{
	
	private static final long serialVersionUID = -5848508079406330009L; //will not change in any version of the API
	
	private SubstitutionType type;
	private int date;
	private String poolDate;
	private String classname;
	private int startTime;
	private String teacher;
	private String subject;
	private String room;
	private String text;

	public Substitution(SubstitutionType type, int date, String classname, int startTime, String teacher, String subject, String room, String text)
	{
		this.type = type;
		this.date = date;
		this.classname = classname.replaceAll("0", "");
		this.startTime = startTime;
		this.teacher = teacher;
		this.subject = subject;
		this.room = room;
		this.text = text;
	}

	public SubstitutionType getType() {
		return type;
	}

	public void setType(SubstitutionType type) {
		this.type = type;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getPoolDate() {
		return poolDate;
	}

	public void setPoolDate(String poolDate) {
		this.poolDate = poolDate;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
}
