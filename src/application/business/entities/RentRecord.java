package application.business.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(of="idRecord")
@Getter @Setter

@Entity
@Table(name="rccRecord")
public class RentRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRecord;
	
	@ManyToOne
	private Driver driver;
	
	@ManyToOne
	private Car car;
	
	private LocalDate rentDate;
	private LocalDate plannedReturnDate;
	
	private LocalDate returnDate;
	private double fuelInTankPercent = -1.;
	private double damagesRepairPrice = -1.;
	
	private double total = -1.;
	private boolean recordClosed = false;
	
	public RentRecord(Driver driver, Car car, LocalDate rentDate, LocalDate plannedReturnDate) {
		super();
		this.driver = driver;
		this.car = car;
		this.rentDate = rentDate;
		this.plannedReturnDate = plannedReturnDate;
	}
	
	
	
	

}
