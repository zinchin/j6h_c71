package application.business.exceptions;

@SuppressWarnings("serial")
public class WrongDateTimePatternException extends RuntimeException {

	public WrongDateTimePatternException(String msg) {
		super (msg);
	}
}
