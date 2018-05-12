package de.airdevelopments.webuntisapi.resultobjects;

import java.io.Serializable;

public class Exam implements Serializable{

	private static final long serialVersionUID = -6386300508648457968L; //will not change in any version of the API
	
	private int id;
	private int[] classes;
	private int[] teachers;
	private int[] students;
	private int subject;
	private int date;
	private int startTime;
	private int endTime;
	
	public Exam(int id, int[] classes, int[] teachers, int[] students, int subject, int date, int startTime, int endTime)
	{
		this.id = id;
		this.classes = classes;
		this.teachers = teachers;
		this.students = students;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getClasses() {
		return classes;
	}

	public void setClasses(int[] classes) {
		this.classes = classes;
	}

	public int[] getTeachers() {
		return teachers;
	}

	public void setTeachers(int[] teachers) {
		this.teachers = teachers;
	}

	public int[] getStudents() {
		return students;
	}

	public void setStudents(int[] students) {
		this.students = students;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
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
