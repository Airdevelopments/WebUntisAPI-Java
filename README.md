# WebUntisAPI for Java
This is a simlpe Java Library/API to access the RESTful JSON RCP WebService from Untis.

The jars in the 'builds' folder contain method and class documentation, which should be shown by your IDE after adding it to the classpath.

1. Installation

	  - download latest build from builds folder / build latest version yourself
	  - download JSON library http://jcenter.bintray.com/org/json/json/20151123/json-20151123.jar
	  - add both .jar files to the build path of your project


2. Quick Use

	The usage of the library is based on the <i>WebUntisConnection</i> class. Note that it is abstract and must be extended by the user to be used properly. That is because method results of the API differ between customers of Untis and can therefore not be generalized. But I created a class that can be used as a template, it already implements certain methods of the Untis API. Note: these might not work for you instantly and requires further adaption.

	<code>ApplicableWebUntisConnection connection = new ApplicableWebUntisConnection("school-sch", "poly", "username", "password");</code>

	Note: The prefix (2. parameter) of the constructor is the sub-url you see in your browser when logging into the web interface of Untis. For example: https://poly.webuntis.com derives to "poly" as the desired prefix. This can change due to changes by Untis and also may vary between customers.
	After the creation of a <i>WebUntisConnection</i> instance, you may now call the <code>WebUntisConnection#login()</code> method to connect to the server:

	<code>
	  if(connection.login())
	  {
	    System.out.println("Login successful!");
	  }
	</code>

	Btw: It is recommended to create a single account with a simple password and read only permissions for use in this library, as sensitive data or passwords may be obtained by third parties through decompilation etc!

	One of the implemented methods in the <i>ApplicableWebUntisConnection</i> is the 'getTeachers' method. So let's invoke it.
	<code>ArrayList<> teachers = connection.getTeachers();</code>
	The result can now be processed in any way you want.

	You should always call <code>connection.logout()</code> when you are finished calling methods to free server resources (as stated in the official WebUntis documentation).


3. Exception Handling

	When calling methods of the library, following exceptions may be thrown:

	<b>WebUntisConnectionErrorException:</b> Thrown when the server responded an error. <code>#getCode(),#getErrorMessage(),#getError()</code> may be used to get detailed information about the error.

	<b>WebUntisConnectionFailureException</b>: Thrown when there has been a problem connecting to the server

	<b>WebUntisConnectionInvalidIDException</b>: Thrown when a result-id does not match up with a request-id (should never happen)

	<b>WebUntisConnectionResultException</b>: Thrown when theres a problem with correctly parsing the result with the JSON library. This might be the case when Untis changed something in their WebAPI
	
	Code example:
		
		ApplicableWebUntisConnection connection = new ApplicableWebUntisConnection("school-sch", "poly", "username", "******");
		
		try
		{
			if(connection.login())
				System.out.println("Login Successful");

			connection.getSubstitutions("20180212", "20181230");
			
			connection.logout();
		}catch(WebUntisConnectionErrorException e)
		{
			if(e.getError() == WebUntisError.NO_RIGHT_FOR_METHOD)
			{
				System.out.println("Permissions need adjustment: " + e.getErrorMessage() + "(" + e.getCode() + ")");
			}
			else if(e.getError() == WebUntisError.METHOD_NOT_FOUND)
			{
				System.out.println("Seems like the API has changed!");
			}
		}


4. Implementing own methods

	You might be interested to implement certain methods yourself, because the results differ to my implementation or I did not even implement that method yet. For that you need to extend the <i>WebUntisConnection</i> class:

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


	This is the basic structure of a method. You need a requestID by calling <code>Utils.getRandomId()</code>, then you can access the connection by calling <code>getConnection()</code> and execute requests with <code>WebUntisHTTPConnector#executeRequest(String request)</code>. The result of an execution should be pre-processed by calling <code>preProcess(String result, String requestID)</code> with the used requestID. This method will handle common exception cases and return a JSONObject that can now be used to gather required information. The rest is up to you, depending on the method and your needs.


5. Feedback

	If you have any wishes/issues/difficulties, tell me!


6. License

	You are allowed to use this library to your needs. You can rebuild it for yourself or use one of the pre-builds. Commericial use is also allowed.
