package application.business.services;

import java.util.List;

import application.business.dto.CarDto;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface AnalistService {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public class StringNumber{
		
		String name;
		Number value;
			
	}

	List<CarDto> getAllCars();
	List<CarDto> getCarsByModel(String idModel);
	RentRecordDto getRecord(int idRecord);
	List<RentRecordDto> getRecordsInDateRange(String from, String to);
	List<RentRecordDto> getAllRecordsUnreceived();
	List<RentRecordDto> getAllRecordsUnclosed();
	List<RentRecordDto> getRecordsDelayedInDateRange(String from, String to);
	List<RentRecordDto> getRecordsDamagedInDateRange(String from, String to);
	List<RentRecordDto> getRecordsByModelInDateRange(String idDriver, String from, String to);
	List<RentRecordDto> getRecordsByCarInDateRange(String idCar, String from, String to);
	List<RentRecordDto> getRecordsByDriverInDateRange(String idDriver, String from, String to);
	double getTotalInDateRange(String from, String to);
	double getTotalByCarInDateRange(String idCar, String from, String to);
	double getTotalByModelInDateRange(String idModel, String from, String to);
	double getTotalByDriverInDateRange(String idDriver, String from, String to);
	double getTotalDelayInDateRange(String from, String to);
	double getTotalDamagesRepairPriceInDateRange(String from, String to);
	double getTotalFuelFeesInDateRange(String from, String to);
	double getIncomeInDateRange(String from, String to);
	double getIncomeByCarInDateRange(String idCar, String from, String to);
	double getIncomeByModelInDateRange(String idModel, String from, String to);
	List<StringNumber> getMostUsedCars();
	List<StringNumber> getMostSaledCars();
	List<StringNumber> getMostUsedModels();
	List<StringNumber> getMostSaledModels();
	List<StringNumber> getMostPayDrivers();
	List<StringNumber> getMostSloopyDrivers();
	List<ModelDto> getAllModels();
	List<ModelDto> getModelsEmpty();

}