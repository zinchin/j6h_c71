package application.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.CarDto;
import application.business.dto.DriverDto;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;
import application.business.services.ClerkService;

@RestController
@CrossOrigin
@RequestMapping("/clerk")
public class ClerkController {
	
	@Autowired ClerkService service;

	@PostMapping("/addDriver")
	public DriverDto addDriver(@RequestBody DriverDto driver) {
		return service.addDriver(driver);
	}
	
	@GetMapping("/getDriver")
	public DriverDto getDriver(String idDriver) {
		return service.getDriver(idDriver);
	}
	
	@PutMapping("/correctDriver")
	public DriverDto correctDriver(@RequestBody DriverDto driver) {
		return service.correctDriver(driver);
	}
	
	@GetMapping("/getRecordsDelayedByDriver")
	public List<RentRecordDto> getRecordsDelayedByDriver(String idDriver){
		return service.getRecordsDelayedByDriver(idDriver);
	}
	
	@GetMapping("/getModelsAvailable")
	public List<ModelDto> getModelsAvailable(){
		return service.getModelsAvailable();
	}
	
	@GetMapping("/getCarsAvailable")
	public List<CarDto> getCarsAvailable(){
		return service.getCarsAvailable();
	};
	
	@GetMapping("/getCarsAvailableByModel")
	public List<CarDto> getCarsAvailableByModel(String idModel){
		return service.getCarsAvailableByModel(idModel);
	}
	
	@GetMapping("/getCar")
	public CarDto getCar(String idCar) {
		return service.getCar(idCar);
	}
	
	@PostMapping("/rentCar")
	public RentRecordDto rentCar(String idCar, String idDriver, String plannedReturnDate) {
		return service.rentCar(idCar, idDriver, plannedReturnDate);
	}
	
	@PutMapping("/receiveCar")
	public RentRecordDto receiveCar(int idRecord, double fuelInTankPercent, boolean noDamages) {
		return service.receiveCar(idRecord, fuelInTankPercent, noDamages);
	}
}
