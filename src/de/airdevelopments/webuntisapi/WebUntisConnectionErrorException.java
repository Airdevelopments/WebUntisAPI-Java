package de.airdevelopments.webuntisapi;

/**
 * Thrown when the result of request contains an error message
 * @author MikisW
 *
 */
public class WebUntisConnectionErrorException extends WebUntisConnectionException {

	private static final long serialVersionUID = -2890236455220873908L;
	
	private WebUntisError error;
	private String errorMessage;
	private int code;
	
	public WebUntisConnectionErrorException(String errorMessage, int code) {
		super("error message: '" + errorMessage + "' : error code: " + code);
		this.error = WebUntisError.getError(code);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public int getCode() {
		return code;
	}

	public WebUntisError getError() {
		return error;
	}
}
