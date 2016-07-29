package de.airdevelopments.webuntisapi;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import de.airdevelopments.webuntisapi.objects.Gender;
import de.airdevelopments.webuntisapi.objects.Room;
import de.airdevelopments.webuntisapi.objects.Student;
import de.airdevelopments.webuntisapi.objects.Subject;
import de.airdevelopments.webuntisapi.objects.Substitution;
import de.airdevelopments.webuntisapi.objects.SubstitutionType;
import de.airdevelopments.webuntisapi.objects.Teacher;


public class WebUntisConnection {
	
	private String sessionID;
	private String password;
	private String user;
	
	private WebUntisHTTPConnector connection;
	
	public WebUntisConnection(String school, String prefix, String user, String password) throws Exception
	{
		this.user = user;
		this.password = password;
		
		this.connection = new WebUntisHTTPConnector(school, prefix);
	}
	
	public Boolean login()
	{
		String id = Utils.getRandomId();
		String result = connection.executeRequest("{\"id\":\""+id+"\",\"method\":\"authenticate\",\"params\":{\"user\":\""+user+"\",\"password\":\""+password+"\", \"client\":\"APP\"},\"jsonrpc\":\"2.0\"}");
		
		JSONObject responseData = new JSONObject(result);
		if(responseData.has("error") || (!responseData.getString("id").equals(id)))
		{
			return false;
		}
		else
		{
			JSONObject innerResult = responseData.getJSONObject("result");
			sessionID = innerResult.getString("sessionId");
			try
			{
				connection.setSessionID(sessionID);
				return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public Boolean hasTimedOut()
	{
		return getTeachers() == null;
	}
	
	public Boolean logout()
	{
		String id = Utils.getRandomId();
		String result = connection.executeRequest("{\"id\":\""+id+"\",\"method\":\"logout\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject responseData = new JSONObject(result);
		if(responseData.has("error") || (!responseData.getString("id").equals(id)))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public ArrayList<Teacher> getTeachers()
	{
		String id = Utils.getRandomId();
		String result = connection.executeRequest("{\"id\":\""+id+"\",\"method\":\"getTeachers\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject responseData = new JSONObject(result);
		if(responseData.has("error") || (!responseData.getString("id").equals(id)))
		{
			return null;
		}
		else
		{
			ArrayList<Teacher> list = new ArrayList<Teacher>();
			JSONArray teachers = responseData.getJSONArray("result");
			
			for(int i = 0;i<teachers.length();i++)
			{
				JSONObject teacher = teachers.getJSONObject(i);
				list.add(new Teacher(teacher.getString("name"), teacher.getString("foreName"), teacher.getString("longName")));
			}
			return list;
		}
	}
	
	public ArrayList<Student> getStudents()
	{
		String id = Utils.getRandomId();
		String result = connection.executeRequest("{\"id\":\""+id+"\",\"method\":\"getStudents\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject responseData = new JSONObject(result);
		if(responseData.has("error") || (!responseData.getString("id").equals(id)))
		{
			return null;
		}
		else
		{
			ArrayList<Student> list = new ArrayList<Student>();
			JSONArray students = responseData.getJSONArray("result");
			
			for(int i = 0;i<students.length();i++)
			{
				JSONObject student = students.getJSONObject(i);
				list.add(new Student(student.getString("name"), student.getString("foreName"), student.getString("longName"), Gender.valueOf(student.getString("gender").toUpperCase())));
			}
			return list;
		}
	}
	
	public ArrayList<de.airdevelopments.webuntisapi.objects.Class> getClasses()
	{
		String id = Utils.getRandomId();
		String result = connection.executeRequest("{\"id\":\""+id+"\",\"method\":\"getKlassen\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject responseData = new JSONObject(result);
		if(responseData.has("error") || (!responseData.getString("id").equals(id)))
		{
			return null;
		}
		else
		{
			ArrayList<de.airdevelopments.webuntisapi.objects.Class> list = new ArrayList<de.airdevelopments.webuntisapi.objects.Class>();
			JSONArray classes = responseData.getJSONArray("result");
			
			for(int i = 0;i<classes.length();i++)
			{
				JSONObject class_ = classes.getJSONObject(i);
				list.add(new de.airdevelopments.webuntisapi.objects.Class(class_.getInt("id"), class_.getString("name"), class_.getString("longName")));
			}
			return list;
		}
	}
	
	public ArrayList<Subject> getSubjects()
	{
		String id = Utils.getRandomId();
		String result = connection.executeRequest("{\"id\":\""+id+"\",\"method\":\"getSubjects\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject responseData = new JSONObject(result);
		if(responseData.has("error") || (!responseData.getString("id").equals(id)))
		{
			return null;
		}
		else
		{
			ArrayList<Subject> list = new ArrayList<Subject>();
			JSONArray subjects = responseData.getJSONArray("result");
			
			for(int i = 0;i<subjects.length();i++)
			{
				JSONObject subject = subjects.getJSONObject(i);
				list.add(new Subject(subject.getString("name"), subject.getString("longName")));
			}
			return list;
		}
	}
	
	public ArrayList<Room> getRooms()
	{
		String id = Utils.getRandomId();
		String result = connection.executeRequest("{\"id\":\""+id+"\",\"method\":\"getRooms\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject responseData = new JSONObject(result);
		if(responseData.has("error") || (!responseData.getString("id").equals(id)))
		{
			return null;
		}
		else
		{
			ArrayList<Room> list = new ArrayList<Room>();
			JSONArray rooms = responseData.getJSONArray("result");
			
			for(int i = 0;i<rooms.length();i++)
			{
				JSONObject room = rooms.getJSONObject(i);
				list.add(new Room(room.getInt("id"), room.getString("name"), room.getString("longName")));
			}
			return list;
		}
	}
	
	public ArrayList<Substitution> getSubstitutions(String startDate, String endDate)
	{
		String id = Utils.getRandomId();
		String result = connection.executeRequest("{\"id\":\""+id+"\",\"method\":\"getSubstitutions\",\"params\":{\"startDate\":"+startDate+",\"endDate\":"+endDate+",\"departmentId\":0},\"jsonrpc\":\"2.0\"}");
		
		JSONObject responseData = new JSONObject(result);
		if(responseData.has("error") || (!responseData.getString("id").equals(id)))
		{
			System.out.println(responseData.toString());
			return null;
		}
		else
		{
			ArrayList<Substitution> list = new ArrayList<Substitution>();
			JSONArray substitutions = responseData.getJSONArray("result");
			for(int i = 0;i<substitutions.length();i++)
			{
				JSONObject substitution = substitutions.getJSONObject(i);
				Boolean text = substitution.has("txt");
				if(substitution.getJSONArray("kl").length() != 0)
				{
					String classname = "";
					JSONArray klassen = substitution.getJSONArray("kl");
					for(int j = 0; j < klassen.length();j++)
					{
						classname += klassen.getJSONObject(j).getString("name") + ", ";
					}
					classname = classname.substring(0, classname.length()-2);
					
					String teacher = "";
					JSONArray teachers = substitution.getJSONArray("te");
					for(int j = 0; j < teachers.length();j++)
					{
						teacher += teachers.getJSONObject(j).getString("name") + ", ";
					}
					teacher = teacher.substring(0, teacher.length()-2);
					
					String subject = "";
					JSONArray subjects = substitution.getJSONArray("su");
					for(int j = 0; j < subjects.length();j++)
					{
						subject += subjects.getJSONObject(j).getString("name") + ", ";
					}
					subject = subject.substring(0, subject.length()-2);
					
					String room = "";
					JSONArray rooms = substitution.getJSONArray("ro");
					for(int j = 0; j < rooms.length();j++)
					{
						room += rooms.getJSONObject(j).getString("name") + ", ";
					}
					room = room.substring(0, room.length()-2);
					
					String txt = null;
					if(text)
						txt = substitution.getString("txt");
					list.add(new Substitution(SubstitutionType.valueOf(substitution.getString("type").toUpperCase()), substitution.getInt("date"), classname, substitution.getInt("startTime"), teacher, subject, room, txt));
				}
			}
			return list;
		}
	}

}
