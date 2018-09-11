package homework2;

/**
 * 
 * @author Hernan Zavala Yanes
 * This class serves as an exception when a cart location is not valid.
 *
 */
public class WrongCartException extends Exception {

	public WrongCartException() {
		super("Wrong cart number! try again.");
	}
}
