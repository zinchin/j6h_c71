package application.common.exceptions;

@SuppressWarnings("serial")
public class DuplicatedIdException extends RuntimeException {
	
	public DuplicatedIdException(String msg) {
		super(msg);
	}
}
