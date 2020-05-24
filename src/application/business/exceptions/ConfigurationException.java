package application.business.exceptions;

@SuppressWarnings("serial")
public class ConfigurationException extends RuntimeException {
	
	public ConfigurationException(String msg) {
		super(msg);
	}
}
