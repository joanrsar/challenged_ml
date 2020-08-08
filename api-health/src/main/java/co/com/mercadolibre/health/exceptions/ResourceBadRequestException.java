package co.com.mercadolibre.health.exceptions;

public class ResourceBadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceBadRequestException() {
		
	}
	
	public ResourceBadRequestException(String message) {
		super(message);
	}
	
	public ResourceBadRequestException(String message,Exception exception) {
		super(message,exception);
	}
	
	
	
	
}
