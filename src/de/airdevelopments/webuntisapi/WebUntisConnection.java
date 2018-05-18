package de.airdevelopments.webuntisapi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The base WebUntisConnection object, supporting login, logout, lastImport and check for persistency of session 
 * @author MikisW
 *
 */
public abstract class WebUntisConnection {
	
	private String user; //user credentials of the customer
	private String password; //password of the customer
	
	private WebUntisHTTPConnector connection; //supporting object for easy access to WebUntis web service server
	
	/**
	 * Base WebUntisConnection, should be extended by a custom class, implementing required functionality. Read more about it on GitHub.
	 * The {@link ApplicableWebUntisConnection}} class implements many of the available methods of the Untis documentation already and may be used as template or quick start.
	 * <br><br>
	 * Note: For the sake of security you should create a new account just for API access with read only permissions and a simple password! Sensitive passwords may be obtained otherwise by decompiling your code!
	 * @param school The school name; can be obtained by logging into customer account on Untis web site and reading the URL parameters in web browser
	 * @param prefix The URL server prefix; can be obtained by logging into customer account on Untis web site and reading the URL prefix in web browser
	 * @param user The username of the account to be used
	 * @param password The password of the account to be used
	 * 
	 * 
	 * @see ApplicableWebUntisConnection
	 */
	public WebUntisConnection(String school, String prefix, String user, String password)
	{
		this.user = user;
		this.password = password;
		
		this.connection = new WebUntisHTTPConnector(school, prefix);
	}
	
	
	/**
	 * Similar to the {@link WebUntisConnection#WebUntisConnection(String, String, String, String)}} constructor, except it is featuring secure login. The constructor does not save the password, therefore calling {@link WebUntisConnection#login()} is not available; instead {@link WebUntisConnection#}
	 * <br><br>
	 * Note: For the sake of security you should create a new account just for API access with read only permissions and a simple password! Sensitive passwords may be obtained otherwise by decompiling your code!
	 * @param school The school name; can be obtained by logging into customer account on Untis web site and reading the URL parameters in web browser
	 * @param prefix The URL server prefix; can be obtained by logging into customer account on Untis web site and reading the URL prefix in web browser
	 * @param user The username of the account to be used
	 * 
	 * 
	 * @see WebUntisConnection
	 */
	public WebUntisConnection(String school, String prefix, String user)
	{
		this.user = user;
		
		this.connection = new WebUntisHTTPConnector(school, prefix);
	}
	
	/**
	 * Executes the 'authenticate' method of the API to log into the given account and start a session. SessionID may be obtained by {@link #getSessionID}!
	 * @return Indicator for successful login. True indicates a valid login, while false indicates a non specific failure.
	 */
	public final boolean login()
	{
		if(password == null)
			throw new IllegalArgumentException("There is no valid password available. Either you called the WebUntisConnection(String school, String prefix, String user) constructor and therefore should use WebUntisConnection#login(char[] password) or the given password was null.");
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = connection.executeRequest("{\"id\":\""+requestID+"\",\"method\":\"authenticate\",\"params\":{\"user\":\""+user+"\",\"password\":\""+password+"\", \"client\":\"APP\"},\"jsonrpc\":\"2.0\"}");
		
		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject
		
		try
		{
			JSONObject innerResult = resultData.getJSONObject("result");
			String sessionID = innerResult.getString("sessionId"); //get the sessionID declared by the server
			
			connection.setSessionID(sessionID); //setting up session id for connection support object
			
			return true; //login successful
		}catch(Exception e) //any issue with reading final results will cause a break in consistency and therefore declare the login as failed
		{
			e.printStackTrace();
			return false; //login failed
		}
	}
	
	/**
	 * Executes the 'authenticate' method of the API to log into the given account and start a session. SessionID may be obtained by {@link #getSessionID}!
	 * @param password Char array is required to guarantee non String pool interactions
	 * @return Indicator for successful login. True indicates a valid login, while false indicates a non specific failure.
	 */
	public final boolean login(char[] password)
	{
		String requestID = Utils.getRandomId(); //generate random id for the request

		String result = connection.executeRequest(Utils.safeReplace("{\"id\":\""+requestID+"\",\"method\":\"authenticate\",\"params\":{\"user\":\""+user+"\",\"password\":\"%password%\", \"client\":\"APP\"},\"jsonrpc\":\"2.0\"}", "%password%", password));
		
		System.out.println(result);
		
		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject
		
		try
		{
			JSONObject innerResult = resultData.getJSONObject("result");
			String sessionID = innerResult.getString("sessionId"); //get the sessionID declared by the server
			
			connection.setSessionID(sessionID); //setting up session id for connection support object
			
			return true; //login successful
		}catch(Exception e) //any issue with reading final results will cause a break in consistency and therefore declare the login as failed
		{
			e.printStackTrace();
			return false; //login failed
		}
	}
	
	/**
	 * Uses the 'getLastestImportTime' method to test if the current session is still active. In an event of success it is guaranteed that the result is small compared to other methods like getTeachers, therefore saving time and maybe download volume on timed connections on mobile devices.
	 * @return Indication if the current session is expired.
	 */
	public boolean hasSessionExpired()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = connection.executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getLatestImportTime\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		try
		{
			JSONObject resultData = new JSONObject(result);
			
			String resultID = resultData.getString("id");
			
			if(resultData.has("error"))
			{
				JSONObject error = resultData.getJSONObject("error");
				if(error.getInt("code") == WebUntisError.NOT_AUTHENTICATED.getCode())
					return true;
			}
			
			if(!resultID.equals(requestID)) //should never happen, though safety first
			{
				throw new WebUntisConnectionInvalidIDException("The result-id does not match the request-id! request-id: " + requestID + "   result-id: " + resultID);
			}
			
			return false;
		}catch(JSONException e)
		{
			//e.printStackTrace(); disabled
			throw new WebUntisConnectionResultException("The result of the request is corrupted or does not match the current implemented standards. This might be due to changes by Untis in the WebUntis JSON RPC API. If you are sure there has been a change by Untis, visit the GitHub page (https://github.com/Luftbaum/WebUntisAPI-Java) and open an issue!");
		}
	}
	
	/**
	 * Executes the 'logout' method of the API to log out of the given account and end the current session. SessionID may be obtained by {@link #getSessionID}! May throw a {@link WebUntisConnectionErrorExcpetion} when not authenticated or session expired!
	 */
	public final void logout()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = connection.executeRequest("{\"id\":\""+requestID+"\",\"method\":\"logout\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		preProcess(result, requestID); //handle common issues, discard result object
	}
	
	/**
	 * Executes the 'getLatestImportTime' method of the API, returning a UNIX timestamp consisting of the date and time of the last import of data. May be used to avoid unnecessary download of information that actually has not changed.
	 * @return The UNIX timestamp of the last time data has been imported to WebUntis
	 */
	public long getLastImport()
	{
		String requestID = Utils.getRandomId(); //generate random id for the request
		String result = connection.executeRequest("{\"id\":\""+requestID+"\",\"method\":\"getLatestImportTime\",\"params\":{},\"jsonrpc\":\"2.0\"}");
		
		JSONObject resultData = preProcess(result, requestID); //handle common issues and catch result as JSONObject
		
		if(resultData.has("result")) //ensure result is persistent
			return resultData.getLong("result");
		else
			return -1; //something went wrong
	}
	
	/**
	 * Returns the current support connection object. Can be invoked by subclasses. Alternatively the direct access of 'connection' is possible.
	 * @return The {@link WebUntisHTTPConnector} used by this connection
	 */
	protected WebUntisHTTPConnector getConnection()
	{
		return this.connection;
	}
	
	/**
	 * Support method, handling common errors and exceptions. Also returns result as processible JSONObject.
	 * @param result The result of a request execution
	 * @param requestID The request-id used for the method call
	 * @return Inputed result String as JSONObject
	 */
	protected JSONObject preProcess(String result, String requestID)
	{
		try
		{
			JSONObject resultData = new JSONObject(result);
			
			String resultID = resultData.getString("id");
			
			if(resultData.has("error"))
			{
				JSONObject error = resultData.getJSONObject("error");
				throw new WebUntisConnectionErrorException(error.getString("message"), error.getInt("code"));
			}
			
			if(!resultID.equals(requestID)) //should never happen, though safety first
			{
				throw new WebUntisConnectionInvalidIDException("The result-id does not match the request-id! request-id: " + requestID + "   result-id: " + resultID);
			}
			
			return resultData;
		}catch(JSONException e)
		{
			//e.printStackTrace(); disabled
			throw new WebUntisConnectionResultException("The result of the request is corrupted or does not match the current implemented standards. This might be due to changes by Untis in the WebUntis JSON RPC API. If you are sure there has been a change by Untis, visit the GitHub page (https://github.com/Luftbaum/WebUntisAPI-Java) and open an issue!");
		}
	}
	
	/**
	 * Returns the current sessionID used. This sessionID might point to an expired session!
	 * @return The sessionID as String
	 */
	public String getSessionID()
	{
		return this.connection.getSessionID();
	}

}
