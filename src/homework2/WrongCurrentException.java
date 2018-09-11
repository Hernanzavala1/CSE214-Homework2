package homework2;
/**
 * 
 * @author Hernan Zavala Yanes
 * This class serves as an exception when a current location is not valid.
 *
 */
public class WrongCurrentException extends Exception {

	public WrongCurrentException() {
		super("Wrong current location entered!");
	}
}
