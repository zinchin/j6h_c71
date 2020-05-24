package application.business.services;

import java.util.List;

import application.business.dto.CarDto;
import application.business.dto.RentRecordDto;

public interface ManagerService {

	List<RentRecordDto> getRecordsUnreceived();
	List<RentRecordDto> getRecordsUnclosed();
	CarDto addCar(CarDto car);
	List<CarDto> getAllCars();
	List<CarDto> getCarsInUse();
	List<CarDto> getCarsOutOfService();
	List<CarDto> getCarsWrittenOff();
	RentRecordDto closeRecord(int idRecord, double damagesRepairPrice);

}