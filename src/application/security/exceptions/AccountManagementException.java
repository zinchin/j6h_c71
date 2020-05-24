package application.security.exceptions;

@SuppressWarnings("serial")
public class AccountManagementException extends RuntimeException {

	public AccountManagementException(String msg) {
		super(msg);
	}
}
