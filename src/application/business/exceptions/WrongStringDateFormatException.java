package application.business.exceptions;

@SuppressWarnings("serial")
public class WrongStringDateFormatException extends RuntimeException {

	public WrongStringDateFormatException(String msg) {
		super(msg);
	}
}
