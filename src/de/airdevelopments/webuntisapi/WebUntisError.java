package de.airdevelopments.webuntisapi;

/**
 * Common errors occurring when using WebUntis web service. When a {@link WebUntisConnectionErrorException} is thrown, the {@link WebUntisConnectionErrorException#getError()} method can be used to obtain the specific error and compare it with the enum statics for case specific exception handling.
 * @author MikisW
 *
 */
public enum WebUntisError {
	
	UNKNOWN_ERROR(0, "unknown error"),
	NOT_AUTHENTICATED(-8520, "not authenticated"),
	INVALID_SCHOOLNAME(-8500, "invalid schoolname"),
	BAD_CREDENTIALS(-8504, "bad credentials"),
	METHOD_NOT_FOUND(-32601, "Method not found"),
	NO_RIGHT_FOR_METHOD(-8509, "no right for #");
	
	private int key;
	private String message;
	
	private WebUntisError(int key, String message)
	{
		this.key = key;
		this.message = message;
	}
	
	public static WebUntisError getError(String errorMessage)
	{
		for(WebUntisError error : WebUntisError.values())
			if(error.message.equalsIgnoreCase(errorMessage))
				return error;
		return WebUntisError.UNKNOWN_ERROR;
	}
	
	public static WebUntisError getError(int errorCode)
	{
		for(WebUntisError error : WebUntisError.values())
			if(error.key == errorCode)
				return error;
		return WebUntisError.UNKNOWN_ERROR;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public int getCode()
	{
		return this.key;
	}
}
