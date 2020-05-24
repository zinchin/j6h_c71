package application.business.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import application.business.config.RccConfig;
import application.business.entities.Car;
import application.business.entities.Driver;
import application.business.entities.Model;
import application.business.entities.RentRecord;
import application.business.exceptions.WrongStringDateFormatException;

@Service
public class BusinessDtoService {
	
	@Value("${rcc.dateformat}")
	private String pattern;
	
	@Autowired 
	DateTimeFormatter dateTimeFormatter;
	
//	@PostConstruct
//	void initFormatter( ) {
//		dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
//	}
	public Function<Car, CarDto> carDto = car -> getCarDto(car);
	public Function<Model, ModelDto> modelDto = model -> getModelDto(model);
	public Function<RentRecord, RentRecordDto> recordDto = record -> getRentRecordDto(record);
	
	public <T,R> List<R> collectionToList(Collection<T> collection, Function<T,R> mapper) {
		return collection.stream().map(t -> mapper.apply(t)).collect(Collectors.toList());
	}
	
	public RccConfig getRccConfig(RccConfigDto config) {
		return new RccConfig(config.getDelayPenalty(), config.getFuelPrice());
	}
	
	public RccConfigDto getRccConfigDto(RccConfig config) {
		return new RccConfigDto(config.getDelayPenalty(), config.getFuelPrice());
	}

	public ModelDto getModelDto(Model model) {
		return new ModelDto(model.getIdModel(), model.getDailyRate(), model.getTankVolume());
	}
	
	public Model getModel(ModelDto model) {
		return new Model(model.getIdModel(), model.getDailyRate(), model.getTankVolume());
	}
	
	public CarDto getCarDto(Car car) {
		return new CarDto(car.getIdCar(), 
				          car.getModel().getIdModel(),
				          car.getState().name());
	}
	
	public Driver getDriver(DriverDto driver) {
		return new Driver(
							driver.getIdDriver(),
							driver.getFirstName(),
							driver.getLastName(),
							parseLocalDate(driver.getBirthDate()),
							driver.getPhoneNumber(),
							driver.getEMail());
	}
	
	public DriverDto getDriverDto(Driver driver) {
		return new DriverDto(
							driver.getIdDriver(),
							driver.getFirstName(),
							driver.getLastName(),
							formatLocalDate(driver.getBirthDate()),
							driver.getPhoneNumber(),
							driver.getEMail());
	}
	
	public RentRecordDto getRentRecordDto(RentRecord record) {
		return new RentRecordDto(
				record.getIdRecord(),
				record.getCar().getIdCar(),
				record.getDriver().getIdDriver(),
				formatLocalDate(record.getRentDate()),
				formatLocalDate(record.getPlannedReturnDate()),
				formatLocalDate(record.getReturnDate()),
				record.getFuelInTankPercent(),
				record.getDamagesRepairPrice(),
				record.getTotal(),
				record.isRecordClosed()
				);
	}
	
	private LocalDate parseLocalDate(String stringDate) {
		try {
			return LocalDate.parse(stringDate,dateTimeFormatter);
		}
		catch(Exception ex) {
			throw new WrongStringDateFormatException("String date " + stringDate 
					+" does not match format " + pattern);
		}	
	}
	
	private String formatLocalDate(LocalDate localDate) {
		return localDate == null ? null : localDate.format(dateTimeFormatter);
	}
	
}
