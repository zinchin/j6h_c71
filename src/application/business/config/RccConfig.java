package application.business.config;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name="rccConfig")
public class RccConfig {
	
	@Id
	int id = 1;
	
	private double delayPenalty;
	private double fuelPrice;
	
	public RccConfig(double delayPenalty, double fuelPrice) {
		super();
		this.delayPenalty = delayPenalty;
		this.fuelPrice = fuelPrice;
	}
	
	
	

}
