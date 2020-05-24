package application.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(of="idCar")
@Getter @Setter

@Entity
@Table(name="rccCar")
public class Car {
	
	@Id
	@Column(length=100)
	private String idCar;    			//registration number
	
	@Enumerated(EnumType.STRING)
	private CarState state = CarState.AVAILABLE;
	
	@ManyToOne
	private Model model;

	public Car(String idCar, Model model) {
		super();
		this.idCar = idCar;
		this.model = model;
	}

	
}
