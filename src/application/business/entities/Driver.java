package application.business.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(of="idDriver")
@Getter @Setter

@Entity
@Table(name="rccDriver")
public class Driver {

	@Id
	@Column(length=100)
	private String idDriver;   // identification document
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String phoneNumber;
	private String eMail;	

	public Driver(String idDriver, String firstName, String lastName, LocalDate birthDate, String phoneNumber,
			String eMail) {
		super();
		this.idDriver = idDriver;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
	}

	
}
