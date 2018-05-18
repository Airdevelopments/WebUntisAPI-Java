package de.airdevelopments.webuntisapi;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import de.airdevelopments.webuntisapi.resultobjects.Gender;
import de.airdevelopments.webuntisapi.resultobjects.Student;

public class MyWebUntisConnection extends WebUntisConnection {

	public MyWebUntisConnection(String school, String prefix, String user, String password) {
		super(school, prefix, user, password);
	}
	
	public ArrayList<Student> getStudents()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = getConnection().executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getStudents\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject

		ArrayList<Student> resultList = new ArrayList<Student>(); //ArrayList to accumulate the resulting student objects
		
		JSONArray students = resultData.getJSONArray("result"); //grab JSONArray out of server result
		for(int i = 0; i < students.length(); i++) //iterate through JSONArray
		{
			JSONObject student = students.getJSONObject(i); //fetch current student as JSONObject
			resultList.add(new Student(student.getInt("id"), student.getString("name"), student.getString("foreName"), student.getString("longName"), Gender.valueOf(student.getString("gender").toUpperCase())));
		}
		return resultList;
	}

}
