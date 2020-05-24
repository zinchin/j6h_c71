package application.business.services;

import java.util.List;

import application.business.dto.CarDto;
import application.business.dto.DriverDto;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;

public interface ClerkService {
	
	
	public DriverDto addDriver(DriverDto driverDto);
	
	public DriverDto getDriver(String idDriver);
	public DriverDto correctDriver(DriverDto driverDto);
	public List<RentRecordDto> getRecordsDelayedByDriver(String idDriver);
	public List<ModelDto> getModelsAvailable();
	public List<CarDto> getCarsAvailable();
	public List<CarDto> getCarsAvailableByModel(String idModel);
	public CarDto getCar(String idCar);
	public RentRecordDto rentCar(String idCar, String idDriver, String plannedReturnDate);
	public RentRecordDto receiveCar(int idRecord, double fuelInTankPercent, boolean noDamages);
	
}