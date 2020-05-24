package application.business.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CarStateConverter implements AttributeConverter<CarState, String>{

	@Override
	public String convertToDatabaseColumn(CarState carState) {
		return carState.name();
	}

	@Override
	public CarState convertToEntityAttribute(String carStateString) {
		return CarState.valueOf(carStateString);
	}
}
