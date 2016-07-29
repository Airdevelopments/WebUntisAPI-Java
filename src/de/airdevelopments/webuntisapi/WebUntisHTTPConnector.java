package de.airdevelopments.webuntisapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

public class WebUntisHTTPConnector {
	
	private String school;
	private HttpsURLConnection connection;
	private String sessionID;
	private String prefix;
	
	public WebUntisHTTPConnector(String school, String prefix) throws Exception
	{
		this.school = school;
		this.prefix = prefix;
	}
	
	public String executeRequest(String request)
	{
		try
		{
			prepareConnection();
			
			StringBuilder result = new StringBuilder();
			
			byte[] requestData = request.getBytes( StandardCharsets.UTF_8 ); //encode request
			
			//Opening Output Stream for request
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			output.write(requestData); //sending our request
			
			//Open Input Stream for the request result
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
			
			return result.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private void prepareConnection() throws Exception
	{
		if(connection != null)
			connection.disconnect();
		//avoid SSL security by allowing any protocol
		SSLContext ctx = SSLContext.getInstance("TLS");
	    ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
	    SSLContext.setDefault(ctx);
	        
	    //HTTP Connection to webservice
	    if(sessionID == null)
	    	connection = (HttpsURLConnection) new URL("https://"+ prefix +".webuntis.com/WebUntis/jsonrpc.do?school="+school).openConnection();
	    else
	    	connection = (HttpsURLConnection) new URL("https://"+ prefix +".webuntis.com/WebUntis/jsonrpc.do;jsessionid="+sessionID+"?school="+school).openConnection();
	    connection.setHostnameVerifier(new HostnameVerifier() {
	    	public boolean verify(String arg0, SSLSession arg1) {
	            return true;
	        }
	    });
	    connection.setRequestProperty("Content-Type", "text/plain");//clear content type for correct input
		connection.setRequestMethod("POST");//must be post for webuntis webservice
		connection.setDoOutput(true);//Allow output for our requests
		connection.setInstanceFollowRedirects(false);//Block failure by auto redirects
		connection.connect();
	}
	
	public void setSessionID(String sessionID) throws Exception
	{
		this.sessionID = sessionID;
	}
	
	public void disconnect() throws Exception
	{
		this.sessionID = null;
		this.connection.disconnect();
	}
	
    private static class DefaultTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

}
