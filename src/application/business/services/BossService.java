package application.business.services;

import application.business.dto.CarDto;
import application.business.dto.ModelDto;
import application.business.dto.RccConfigDto;

public interface BossService {

	RccConfigDto setConfig(RccConfigDto config);
	ModelDto addModel(ModelDto model);
	ModelDto setModelDailyRate(String idModel, double dailyRate);
	CarDto setCarWrittenOff(String idCar);

}