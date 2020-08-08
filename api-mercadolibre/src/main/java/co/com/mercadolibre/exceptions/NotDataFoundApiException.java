package co.com.mercadolibre.exceptions;

public class NotDataFoundApiException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private Exception exception;
	private String message;
	
	public NotDataFoundApiException(Exception exception, String message) {
		this.exception = exception;
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
