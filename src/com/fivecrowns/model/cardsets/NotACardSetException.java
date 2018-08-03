package com.fivecrowns.model.cardsets;

/**
 * Exception class thrown when a book or a run is invalid
 * 
 * @author Calvin
 *
 */
public class NotACardSetException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7770983632518572261L;

	public NotACardSetException() {}
	
	public NotACardSetException(String message) {
		super(message);
	}
}
