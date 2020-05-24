package application.business.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import application.business.config.RccConfig;
import application.business.dto.CarDto;
import application.business.dto.DriverDto;
import application.business.dto.BusinessDtoService;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;
import application.business.entities.Car;
import application.business.entities.CarState;
import application.business.entities.Driver;
import application.business.entities.Model;
import application.business.entities.RentRecord;
import application.business.exceptions.ConfigurationException;
import application.business.exceptions.RecordException;
import application.business.repositories.CarJpaRepository;
import application.business.repositories.DriverJpaRepository;
import application.business.repositories.ModelJpaRepository;
import application.business.repositories.RccConfigJpaRepository;
import application.business.repositories.RentRecordJpaRepository;

@Service
public class ClerkServiceImpl implements ClerkService {
	
	@Autowired CarJpaRepository carRepo;
	@Autowired ModelJpaRepository modelRepo;
	@Autowired DriverJpaRepository driverRepo;
	@Autowired RentRecordJpaRepository recordRepo;
	@Autowired BusinessDtoService dtoService;
	@Autowired RccConfigJpaRepository configRepo;
	@Autowired DateTimeFormatter dtf;
	
	@Transactional
	@Override
	public DriverDto addDriver(DriverDto driverDto) {
		driverRepo.checkById(driverDto.getIdDriver(), false);
		driverRepo.save(dtoService.getDriver(driverDto));
		return driverDto;
	}
	
	@Transactional(readOnly = true)
	@Override
	public DriverDto getDriver(String idDriver) {
		return dtoService.getDriverDto(driverRepo.checkById(idDriver, true));
	}
	
	@Transactional
	@Override
	public DriverDto correctDriver(DriverDto driverDto) {
		driverRepo.checkById(driverDto.getIdDriver(), true);
		driverRepo.save(dtoService.getDriver(driverDto));
		return driverDto;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<RentRecordDto> getRecordsDelayedByDriver(String idDriver) {	
		List<RentRecord> result = recordRepo.findByDriverDelayed(idDriver);
		if (result.size() == 0) driverRepo.checkById(idDriver, true);
		return dtoService.collectionToList(result, dtoService.recordDto);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<ModelDto> getModelsAvailable(){
		return dtoService.collectionToList(modelRepo.findAll()
                .stream()
                .filter(model -> hasNotAvailable(model))
                .collect(Collectors.toList()), 
                dtoService.modelDto);
	}
	
	private boolean hasNotAvailable(Model model) {
		return model.getCars().stream()
		              .filter(car -> car.getState() == CarState.AVAILABLE)
		              .collect(Collectors.toList()).size() > 0;
		              
	}

	@Override
	public List<CarDto> getCarsAvailable(){
		return dtoService.collectionToList(carRepo.findByStateEquals(CarState.AVAILABLE), dtoService.carDto);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<CarDto> getCarsAvailableByModel(String idModel){
		return dtoService.collectionToList(carRepo.findByModelEqualsAndStateEquals(modelRepo.checkById(idModel, true),CarState.AVAILABLE), 
				                                                             dtoService.carDto);
	}
	
	@Transactional(readOnly = true)
	@Override
	public CarDto getCar(String idCar) {
		return dtoService.getCarDto(carRepo.checkById(idCar, true));
	}
	
	@Transactional
	@Override
	public RentRecordDto rentCar(String idCar, String idDriver, String plannedReturnDate) {
		Driver driver = driverRepo.checkById(idDriver, true);
		Car car = carRepo.checkById(idCar, true);
		LocalDate today = LocalDate.now();
		LocalDate planned = LocalDate.parse(plannedReturnDate);
		if (planned.isBefore(today))
			throw new RecordException("Planned return date "+plannedReturnDate+ " precedes the current one");
		
		RentRecord record = new RentRecord(driver, car, today, planned);
		car.setState(CarState.IN_USE);
		recordRepo.save(record);
		return dtoService.getRentRecordDto(record);	
	}
	
	@Transactional
	@Override
	public RentRecordDto receiveCar(int idRecord, double fuelInTankPercent, boolean noDamages) {
		RentRecord record = recordRepo.checkById(idRecord, true);
		record.setReturnDate(LocalDate.now());
		record.setFuelInTankPercent(fuelInTankPercent);
		record.setTotal(computeTotal(record));
		if (noDamages) {
			record.setDamagesRepairPrice(0.);
			record.setRecordClosed(true);
			record.getCar().setState(CarState.AVAILABLE);
		}
		else {
			record.getCar().setState(CarState.OUT_OF_SERVICE);
		}
		
		return dtoService.getRentRecordDto(record);
	}
	
	private double computeTotal(RentRecord record) {
		RccConfig config = configRepo.findById(1).orElse(null);
		if (config == null) {
			throw new ConfigurationException("Business configuration not set");
		}
		
		Model model = record.getCar().getModel();
		int rentDays = Period.between(record.getPlannedReturnDate(), record.getRentDate()).getDays();
		int delayDays = Integer.max(0, Period.between(record.getReturnDate(), record.getPlannedReturnDate()).getDays());
		
		return model.getDailyRate()*(rentDays + delayDays*config.getDelayPenalty()) +
				config.getFuelPrice()*model.getTankVolume()*(1. - record.getFuelInTankPercent()/100.);
						   
	}
	
	
	
	
	
	
	
}
