package de.airdevelopments.webuntisapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * A connection support class for easier access to WebUntis web service. Most low-level connection exception handling is already implemented.
 * @author MikisW
 *
 */
public class WebUntisHTTPConnector {
	
	private String school; //school parameter for URL
	private HttpsURLConnection connection; //current server connection
	private String sessionID; //current sessionID as described in Untis official API documentation
	private String prefix; //prefix of URL server, varies between customers
	
	/**
	 * Creates a connection support object for easy access to WebUntis web service
	 * @param school The school name; can be obtained by logging into customer account on Untis web site and reading the URL parameters in web browser
	 * @param prefix The URL server prefix; can be obtained by logging into customer account on Untis web site and reading the URL prefix in web browser
	 */
	public WebUntisHTTPConnector(String school, String prefix)
	{
		this.school = school;
		this.prefix = prefix;
	}
	
	/**
	 * Executes a request for the WebUntis web service. Be sure only to use supported requests as defined in the 'WebUntis JSON-RPC API' documentation by Untis.
	 * <br><br>
	 * Note: a non 'null' result may not indicate a successful execution of the request!
	 * @param request The result of the executed request. Usually a JSON formatted String. See official documentation by Untis for details.
	 * @return Result of the given request
	 */
	public String executeRequest(String request)
	{
		prepareConnection(); //ensure a connection to the server is established
		try
		{
			StringBuilder result = new StringBuilder(); //forms the resulting string of the request
			
			byte[] requestData = request.getBytes( Charset.forName("UTF-8") ); //encode request
			
			//opening output stream for request
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			output.write(requestData); //sending out request
			output.flush(); //ensure complete writing for long requests
			output.close(); //close output steam
			
			//opening input stream for the request result
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) { //accumulate data into StringBuilder 'result'
				result.append(line);
			}
			rd.close(); //close input stream
			
			disconnect(); //disconnect from server to save resources; disconnecting does not mean the session has expired!
			
			return result.toString(); //return result from request (should be JSON formatted string)
		}catch(IOException e)
		{
			throw new WebUntisConnectionFailureException("The connection to the server has been disturbed. Try again, if error persists check internet connection and permissions for sending and receiving data. Also a malformed request string could be the issue, ensure UTF-8 range.");
		}
	}
	
	/**
	 * Prepares a fresh connection to the web service.
	 */
	private void prepareConnection()
	{
		if(connection != null)
			disconnect(); //ensure last connection was correctly closed
		
		String url = null; //target URL for connection to web service
		
		try
		{
		
			//avoid SSL security by allowing any protocol
			SSLContext ctx = SSLContext.getInstance("TLS");
		    ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
		    SSLContext.setDefault(ctx);
		    
		    if(sessionID == null)
		    	url = "https://"+ prefix +".webuntis.com/WebUntis/jsonrpc.do?school="+school; //currently not in a session, only login method will be available
		    else
		    	url = "https://"+ prefix +".webuntis.com/WebUntis/jsonrpc.do;jsessionid="+sessionID+"?school="+school; //reuse current session
		    
		    //HTTP connection to web service
		    connection = (HttpsURLConnection) new URL(url).openConnection();
		   
		    connection.setHostnameVerifier(new HostnameVerifier() {
		    	public boolean verify(String arg0, SSLSession arg1) {
		            return true;
		        }
		    });
		    connection.setRequestProperty("Content-Type", "text/plain"); //clear content type for correct input
			connection.setRequestMethod("POST"); //must be post for WebUntis web service (as specified in API documentation by Untis)
			connection.setDoOutput(true); //allow output for our requests
			connection.setInstanceFollowRedirects(false); //avoid failure by auto redirects
			connection.connect(); //finally connect to the server
		}
		catch(NoSuchAlgorithmException | KeyManagementException | MalformedURLException e)
		{
			//should never be the case, if so, API needs update due to changes in security protocols
			e.printStackTrace();
		} catch (IOException e) {
			//connection issues
			throw new WebUntisConnectionFailureException("The connection to '" + url + "' could not be established. Ensure you have a valid internet connection (also no port restrictions etc) and used the correct prefix for the URL of the webservice (you used '" + prefix + "')!");
		}
	}
	
	/**
	 * Sets the current sessionID. This affects the functionality of {@link #executeRequest} as most methods are only available in a valid session.
	 * @param sessionID
	 */
	public void setSessionID(String sessionID)
	{
		this.sessionID = sessionID;
	}
	
	/**
	 * Returns the current sessionID used. This sessionID might point to an expired session!
	 * @return
	 */
	public String getSessionID() {
		// TODO Auto-generated method stub
		return this.sessionID;
	}
	
	/**
	 * Disconnects current connection from web service. This method should always be called to free resources and prevent interference between multiple connections.
	 */
	public void disconnect()
	{
		this.connection.disconnect();
		this.connection = null;
	}
	
	/**
	 * Ensures connection to web service is possible by using security protocol overrides of defaults
	 * @author MikisW
	 */
    private static class DefaultTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

}
