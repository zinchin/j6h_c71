package application.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import application.business.dto.CarDto;
import application.business.dto.BusinessDtoService;
import application.business.dto.RentRecordDto;
import application.business.entities.Car;
import application.business.entities.CarState;
import application.business.entities.RentRecord;
import application.business.exceptions.RecordException;
import application.business.repositories.CarJpaRepository;
import application.business.repositories.ModelJpaRepository;
import application.business.repositories.RentRecordJpaRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired CarJpaRepository carRepo;
	@Autowired ModelJpaRepository modelRepo;
	@Autowired RentRecordJpaRepository recordRepo;
	@Autowired BusinessDtoService dtoService;
	
	@Override
	@Transactional(readOnly = true)
	public List<RentRecordDto> getRecordsUnreceived(){
		return dtoService.collectionToList(recordRepo.findByReturnDateIsNull(), dtoService.recordDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RentRecordDto> getRecordsUnclosed(){
		return dtoService.collectionToList(recordRepo.findByRecordClosedFalse(), dtoService.recordDto);
	}
	
	@Override
	@Transactional
	public CarDto addCar(CarDto carDto) {	
		carRepo.checkById(carDto.getIdCar(), false);
		carRepo.save(new Car(carDto.getIdCar(), modelRepo.checkById(carDto.getIdModel(), true)));
		return carDto;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CarDto> getAllCars(){
		return dtoService.collectionToList(carRepo.findAll(), dtoService.carDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CarDto> getCarsInUse(){
		return dtoService.collectionToList(carRepo.findByStateEquals(CarState.IN_USE), dtoService.carDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CarDto> getCarsOutOfService(){
		return dtoService.collectionToList(carRepo.findByStateEquals(CarState.OUT_OF_SERVICE), dtoService.carDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CarDto> getCarsWrittenOff(){
		return dtoService.collectionToList(carRepo.findByStateEquals(CarState.WRITTEN_OFF), dtoService.carDto);
	}
	
	@Override
	@Transactional
	public RentRecordDto closeRecord(int idRecord, double damagesRepairPrice) {
		RentRecord record = recordRepo.checkById(idRecord, true);
		
		if (record.isRecordClosed())
			throw new RecordException("Record with id "+idRecord+" is closed");
		
		if (record.getReturnDate() == null)
			throw new RecordException("Car by record with id "+idRecord+" not returned");
	
		record.setDamagesRepairPrice(damagesRepairPrice);
		record.setTotal(record.getTotal() + damagesRepairPrice);
		record.getCar().setState(CarState.AVAILABLE);
		record.setRecordClosed(true);
		
		return dtoService.getRentRecordDto(record);
	}
}
