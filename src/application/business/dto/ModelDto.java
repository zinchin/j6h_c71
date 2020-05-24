package application.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModelDto {
	
	private String idModel;
	private double dailyRate;
	private double tankVolume;

}
