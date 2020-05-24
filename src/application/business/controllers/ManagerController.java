package application.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.CarDto;
import application.business.dto.RentRecordDto;
import application.business.services.ManagerService;

@RestController
@CrossOrigin
@RequestMapping("/manager")
public class ManagerController {

	@Autowired ManagerService service;
	
	@GetMapping("/getRecordsUnreceived")
	List<RentRecordDto> getRecordsUnreceived(){
		return service.getRecordsUnreceived();
	}
	
	@GetMapping("/getRecordsUnclosed")
	List<RentRecordDto> getRecordsUnclosed(){
		return service.getRecordsUnclosed();
	}
	
	@PostMapping("/addCar")
	CarDto addCar(CarDto car) {
		return service.addCar(car);
	}
	
	@GetMapping("/getAllCars")
	List<CarDto> getAllCars(){
		return service.getAllCars();
	}
	
	@GetMapping("/getCarsInUse")
	List<CarDto> getCarsInUse(){
		return service.getCarsInUse();
	}
	
	@GetMapping("/getCarsOutOfService")
	List<CarDto> getCarsOutOfService(){
		return service.getCarsOutOfService();
	}
	
	@GetMapping("/getCarsWrittenOff")
	List<CarDto> getCarsWrittenOff(){
		return service.getCarsWrittenOff();
	}
	
	@PutMapping("/closeRecord")
	RentRecordDto closeRecord(int idRecord, double damagesRepairPrice) {
		return service.closeRecord(idRecord, damagesRepairPrice);
	}
}
