package de.airdevelopments.webuntisapi;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import de.airdevelopments.webuntisapi.resultobjects.BaseClass;
import de.airdevelopments.webuntisapi.resultobjects.Department;
import de.airdevelopments.webuntisapi.resultobjects.Exam;
import de.airdevelopments.webuntisapi.resultobjects.Gender;
import de.airdevelopments.webuntisapi.resultobjects.Holiday;
import de.airdevelopments.webuntisapi.resultobjects.Room;
import de.airdevelopments.webuntisapi.resultobjects.Student;
import de.airdevelopments.webuntisapi.resultobjects.Subject;
import de.airdevelopments.webuntisapi.resultobjects.Substitution;
import de.airdevelopments.webuntisapi.resultobjects.SubstitutionType;
import de.airdevelopments.webuntisapi.resultobjects.Teacher;
import de.airdevelopments.webuntisapi.resultobjects.TimeGridUnit;
import de.airdevelopments.webuntisapi.resultobjects.TimeUnit;

/**
 * This is a default extension of the {@link WebUntisConnection}, providing implementations from most of the methods of the WebUntis JSON RPC API. It may be used directly or extended for further implementation.
 * @author MikisW
 *
 */
public class ApplicableWebUntisConnection extends WebUntisConnection{

	/**
	 * Implements many of the available methods of the Untis documentation already and may be used as template or quick start.
	 * <br><br>
	 * Note: For the sake of security you should create a new account just for API access with read only permissions and a simple password! Sensitive passwords may be obtained otherwise by decompiling your code!
	 * @param school The school name; can be obtained by logging into customer account on Untis web site and reading the URL parameters in web browser
	 * @param prefix The URL server prefix; can be obtained by logging into customer account on Untis web site and reading the URL prefix in web browser
	 * @param user The username of the account to be used
	 * @param password The password of the account to be used
	 * 
	 * @see WebUntisConnection
	 */
	public ApplicableWebUntisConnection(String school, String prefix, String user, String password) {
		super(school, prefix, user, password);
	}
	
	
	/**
	 * Executes the 'getTeachers' method, returning all teachers in an ArrayList. Teachers are represented using {@link Teacher} objects.
	 * @return An ArrayList consisting of all resulting teachers
	 */
	public ArrayList<Teacher> getTeachers()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = getConnection().executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getTeachers\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject
		
		ArrayList<Teacher> resultList = new ArrayList<Teacher>(); //ArrayList to accumulate the resulting teacher objects
		
		JSONArray teachersArray = resultData.getJSONArray("result"); //grab JSONArray out of server result
		for(int i = 0; i < teachersArray.length(); i++) //iterate through JSONArray
		{
			JSONObject teacher = teachersArray.getJSONObject(i); //fetch current teacher as JSONObject
			resultList.add(new Teacher(teacher.getInt("id"), teacher.getString("name"), teacher.getString("foreName"), teacher.getString("longName"), teacher.getString("title"), teacher.getBoolean("active")));
		}
		
		return resultList;
	}
	
	/**
	 * Executes the 'getStudents' method, returning all students in an ArrayList. Students are represented using {@link Student} objects.
	 * @return An ArrayList consisting of all resulting students
	 */
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

	/**
	 * Executes the 'getKlassen' method, returning all classes in an ArrayList. Classes are represented using {@link BaseClass} objects.
	 * @return An ArrayList consisting of all resulting students
	 */
	public ArrayList<BaseClass> getClasses()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = getConnection().executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getKlassen\",\"params\":{},\"jsonrpc\":\"2.0\"}");

		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject

		ArrayList<BaseClass> resultList = new ArrayList<BaseClass>(); //ArrayList to accumulate the resulting class objects
		
		JSONArray classes = resultData.getJSONArray("result"); //grab JSONArray out of server result
		for(int i = 0; i < classes.length(); i++) //iterate through JSONArray
		{
			JSONObject baseClass = classes.getJSONObject(i); //fetch current class as JSONObject
			resultList.add(new BaseClass(baseClass.getInt("id"), baseClass.getString("name"), baseClass.getString("longName"), baseClass.getBoolean("active")));
		}
		return resultList;
	}
	
	/**
	 * Executes the 'getSubjects' method, returning all subjects in an ArrayList. Subjects are represented using {@link Subject} objects.
	 * @return An ArrayList consisting of all resulting subjects
	 */
	public ArrayList<Subject> getSubjects()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = getConnection().executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getSubjects\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject
		
		ArrayList<Subject> resultList = new ArrayList<Subject>(); //ArrayList to accumulate the resulting subject objects
		
		JSONArray subjects = resultData.getJSONArray("result"); //grab JSONArray out of server result
		for(int i = 0; i < subjects.length(); i++) //iterate through JSONArray
		{
			JSONObject subject = subjects.getJSONObject(i); //fetch current subject as JSONObject
			resultList.add(new Subject(subject.getInt("id"), subject.getString("name"), subject.getString("longName"), subject.getString("alternateName"), subject.getBoolean("active")));
		}
		return resultList;
	}
	
	
	/**
	 * Executes the 'getRooms' method, returning all rooms in an ArrayList. Subjects are represented using {@link Room} objects.
	 * @return An ArrayList consisting of all resulting rooms
	 */
	public ArrayList<Room> getRooms()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = getConnection().executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getRooms\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject
		
		ArrayList<Room> resultList = new ArrayList<Room>(); //ArrayList to accumulate the resulting room objects
		
		JSONArray rooms = resultData.getJSONArray("result"); //grab JSONArray out of server result
		for(int i = 0;i<rooms.length();i++) //iterate through JSONArray
		{
			JSONObject room = rooms.getJSONObject(i); //fetch current room as JSONObject
			resultList.add(new Room(room.getInt("id"), room.getString("name"), room.getString("longName"), room.getBoolean("active"), room.getString("building")));
		}
		return resultList;
	}
	
	/**
	 * Executes the 'getDepartments' method, returning all departments in an ArrayList. Departments are represented using {@link Department} objects.
	 * @return An ArrayList consisting of all resulting departments
	 */
	public ArrayList<Department> getDepartments()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = getConnection().executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getDepartments\",\"params\":{},\"jsonrpc\":\"2.0\"}");

		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject
		
		ArrayList<Department> resultList = new ArrayList<Department>(); //ArrayList to accumulate the resulting department objects
		
		JSONArray departments = resultData.getJSONArray("result"); //grab JSONArray out of server result
		for(int i = 0; i < departments.length(); i++) //iterate through JSONArray
		{
			JSONObject department = departments.getJSONObject(i); //fetch current department as JSONObject
			resultList.add(new Department(department.getInt("id"), department.getString("name"), department.getString("longName")));
		}
		return resultList;
	}
	
	
	/**
	 * Executes the 'getHolidays' method, returning all holidays in an ArrayList. Holidays are represented using {@link Holiday} objects.
	 * @return An ArrayList consisting of all resulting holidays
	 */
	public ArrayList<Holiday> getHolidays()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = getConnection().executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getHolidays\",\"params\":{},\"jsonrpc\":\"2.0\"}");

		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject
		
		ArrayList<Holiday> resultList = new ArrayList<Holiday>(); //ArrayList to accumulate the resulting department objects
		
		JSONArray holidays = resultData.getJSONArray("result"); //grab JSONArray out of server result
		for(int i = 0; i < holidays.length(); i++) //iterate through JSONArray
		{
			JSONObject holiday = holidays.getJSONObject(i); //fetch current department as JSONObject
			resultList.add(new Holiday(holiday.getInt("id"), holiday.getString("name"), holiday.getString("longName"), holiday.getInt("startDate"), holiday.getInt("endDate")));
		}
		return resultList;
	}
	
	
	/**
	 * Executes the 'getTimegridUnits' method, returning all time grid units in an ArrayList. Time grid units are represented using {@link TimeGridUnit} objects.
	 * @return An ArrayList consisting of all resulting time grid units
	 */
	public ArrayList<TimeGridUnit> getTimegridUnits()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = getConnection().executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getTimegridUnits\",\"params\":{},\"jsonrpc\":\"2.0\"}");

		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject
		
		ArrayList<TimeGridUnit> resultList = new ArrayList<TimeGridUnit>(); //ArrayList to accumulate the resulting time grid unit objects
		
		JSONArray timegridUnits = resultData.getJSONArray("result"); //grab JSONArray out of server result
		for(int i = 0; i < timegridUnits.length(); i++) //iterate through JSONArray
		{
			JSONObject timegridUnit = timegridUnits.getJSONObject(i); //fetch current time grid unit as JSONObject
			
			ArrayList<TimeUnit> units = new ArrayList<TimeUnit>();
			
			JSONArray timeUnits = timegridUnit.getJSONArray("timeUnits");
			for(int j = 0; j < timeUnits.length(); j++)
			{
				JSONObject timeUnit = timeUnits.getJSONObject(i);
				units.add(new TimeUnit(timeUnit.getString("name"), timeUnit.getInt("startTime"), timeUnit.getInt("endTime")));
			}
			
			resultList.add(new TimeGridUnit(timegridUnit.getInt("day"), units));
		}
		return resultList;
	}
	
	/**
	 * Executes the 'getExams' method, returning all exams in an ArrayList. Exams are represented using {@link Exam} objects.
	 * @param startDate The date to start the search for exams. Format as defined in Untis API documentation: "YYYYMMDD"
	 * @param endDate The date to end the search for exams. Format as defined in Untis API documentation: "YYYYMMDD"
	 * @return An ArrayList consisting of all resulting exams
	 */
	public ArrayList<Exam> getExams(String startDate, String endDate)
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = getConnection().executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getExams\",\"params\":{\"examTypeId\":\""+0+"\", \"startDate\":\""+startDate+"\", \"endDate\":\""+endDate+"\"},\"jsonrpc\":\"2.0\"}");

		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject

		ArrayList<Exam> resultList = new ArrayList<Exam>(); //ArrayList to accumulate the resulting exam objects
		
		JSONArray exams = resultData.getJSONArray("result"); //grab JSONArray out of server result
		for(int i = 0; i < exams.length(); i++) //iterate through JSONArray
		{
			JSONObject exam = exams.getJSONObject(i); //fetch current exam as JSONObject
			resultList.add(new Exam(exam.getInt("id"), Utils.toIntArray(exam.getJSONArray("classes")), Utils.toIntArray(exam.getJSONArray("teachers")), Utils.toIntArray(exam.getJSONArray("students")), exam.getInt("subject"), exam.getInt("date"), exam.getInt("startTime"), exam.getInt("endTime")));
		}
		return resultList;
	}
	
	/**
	 * Executes the 'getSubstitutions' method, returning all substitutions in an ArrayList. Substitutions are represented using {@link Substitution} objects.
	 * @param startDate The date to start the search for substitutions. Format as defined in Untis API documentation: "YYYYMMDD"
	 * @param endDate The date to end the search for substitutions. Format as defined in Untis API documentation: "YYYYMMDD"
	 * @return An ArrayList consisting of all resulting substitutions
	 */
	public ArrayList<Substitution> getSubstitutions(String startDate, String endDate)
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = getConnection().executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getSubstitutions\",\"params\":{\"startDate\":"+startDate+",\"endDate\":"+endDate+",\"departmentId\":0},\"jsonrpc\":\"2.0\"}");
		
		System.out.println(result);
		
		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject

		ArrayList<Substitution> resultList = new ArrayList<Substitution>(); //ArrayList to accumulate the resulting substitution objects
		
		JSONArray substitutions = resultData.getJSONArray("result"); //grab JSONArray out of server result
		for(int i = 0; i < substitutions.length(); i++) //iterate through JSONArray
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
				if(klassen.length() > 0)
					classname = classname.substring(0, classname.length()-2);
				
				String teacher = "";
				JSONArray teachers = substitution.getJSONArray("te");
				for(int j = 0; j < teachers.length();j++)
				{
					teacher += teachers.getJSONObject(j).getString("name") + ", ";
				}
				if(teachers.length() > 0)
					teacher = teacher.substring(0, teacher.length()-2);
				
				String subject = "";
				JSONArray subjects = substitution.getJSONArray("su");
				for(int j = 0; j < subjects.length();j++)
				{
					subject += subjects.getJSONObject(j).getString("name") + ", ";
				}
				if(subjects.length() > 0)
					subject = subject.substring(0, subject.length()-2);
				
				String room = "";
				JSONArray rooms = substitution.getJSONArray("ro");
				for(int j = 0; j < rooms.length();j++)
				{
					room += rooms.getJSONObject(j).getString("name") + ", ";
				}
				if(rooms.length() > 0)
					room = room.substring(0, room.length()-2);
				
				String txt = null;
				if(text)
					txt = substitution.getString("txt");
				
				try
				{
					resultList.add(new Substitution(SubstitutionType.valueOf(substitution.getString("type").toUpperCase()), substitution.getInt("date"), classname, substitution.getInt("startTime"), teacher, subject, room, txt));
				}
				catch(IllegalArgumentException e)
				{
					resultList.add(new Substitution(SubstitutionType.UNKNOWN, substitution.getInt("date"), classname, substitution.getInt("startTime"), teacher, subject, room, txt));
				}
			}
		}
		return resultList;
	}
	
	
}
