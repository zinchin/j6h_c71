package application.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
	
	private String idCar;
	private String idModel;
	private String carState = "AVAILABLE";
	
	public CarDto(String idCar, String idModel) {
		super();
		this.idCar = idCar;
		this.idModel = idModel;
	}
	
	
	
}
