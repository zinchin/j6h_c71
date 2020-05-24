package application.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverDto {
	
	private String idDriver;   	
	private String firstName;
	private String lastName;
	private String birthDate;
	private String phoneNumber;
	private String eMail;

}
