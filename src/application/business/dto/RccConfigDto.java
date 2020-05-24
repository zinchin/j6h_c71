package application.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RccConfigDto {
	
	private double delayPenalty;
	private double fuelPrice;

}
