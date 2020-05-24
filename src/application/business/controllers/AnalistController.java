package application.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.CarDto;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;
import application.business.services.AnalistService;
import application.business.services.AnalistService.StringNumber;

@RestController
@CrossOrigin
@RequestMapping("/analist")
public class AnalistController {
	
	@Autowired AnalistService service;
	
	@GetMapping("/getAllCars")
	List<CarDto> getAllCars(){
		return service.getAllCars();
	}
	
	@GetMapping("/getCarsByModel")
	List<CarDto> getCarsByModel(String idModel){
		return service.getCarsByModel(idModel);
	}
	
	@GetMapping("/getRecord")
	RentRecordDto getRecord(int idRecord) {
		return service.getRecord(idRecord);
	}
	
	@GetMapping("/getRecordsInDateRange")
	List<RentRecordDto> getRecordsInDateRange(String from, String to){
		return service.getRecordsInDateRange(from, to);
	}
	
	@GetMapping("/getAllRecordsUnreceived")
	List<RentRecordDto> getAllRecordsUnreceived( ){
		return service.getAllRecordsUnreceived();
	}
	
	@GetMapping("/getAllRecordsUnclosed")
	List<RentRecordDto> getAllRecordsUnclosed(){
		return service.getAllRecordsUnclosed();
	}
	
	@GetMapping("/getRecordsDelayedInDateRange")
	List<RentRecordDto> getRecordsDelayedInDateRange(String from, String to){
		return service.getRecordsDelayedInDateRange(from, to);
	}
	
	@GetMapping("/getRecordsDamagedInDateRange")
	List<RentRecordDto> getRecordsDamagedInDateRange(String from, String to){
		return service.getRecordsDamagedInDateRange(from, to);
	}
	
	@GetMapping("/getRecordsByModelInDateRange")
	List<RentRecordDto> getRecordsByModelInDateRange(String idDriver, String from, String to){
		return service.getRecordsByModelInDateRange(idDriver, from, to);
	}
	
	@GetMapping("/getRecordsByCarInDateRang")
	List<RentRecordDto> getRecordsByCarInDateRange(String idCar, String from, String to){
		return service.getRecordsByCarInDateRange(idCar, from, to);
	}
	
	@GetMapping("/getRecordsByDriverInDateRange")
	List<RentRecordDto> getRecordsByDriverInDateRange(String idDriver, String from, String to){
		return service.getRecordsByDriverInDateRange(idDriver, from, to);
	}
	
	@GetMapping("/getTotalInDateRange")
	double getTotalInDateRange(String from, String to) {
		return service.getTotalInDateRange(from, to);
	}
	
	@GetMapping("/getTotalByCarInDateRange")
	double getTotalByCarInDateRange(String idCar, String from, String to) {
		return service.getTotalByCarInDateRange(idCar, from, to);
	}
	
	@GetMapping("/ getTotalByModelInDateRange")
	double getTotalByModelInDateRange(String idModel, String from, String to) {
		return service.getTotalByModelInDateRange(idModel, from, to);
	}
	
	@GetMapping("/getTotalByDriverInDateRange")
	double getTotalByDriverInDateRange(String idDriver, String from, String to) {
		return service.getTotalByDriverInDateRange(idDriver, from, to);
	}
	
	@GetMapping("/getTotalDelayInDateRange")
	double getTotalDelayInDateRange(String from, String to) {
		return service.getTotalDelayInDateRange(from, to);
	}
	
	@GetMapping("/")
	double getTotalDamagesRepairPriceInDateRange(String from, String to) {
		return service.getTotalDamagesRepairPriceInDateRange(from, to);
	}
	
	@GetMapping("/getTotalDamagesRepairPriceInDateRange")
	double getTotalFuelFeesInDateRange(String from, String to) {
		return service.getTotalFuelFeesInDateRange(from, to);
	}
	
	@GetMapping("/getIncomeInDateRange")
	double getIncomeInDateRange(String from, String to) {
		return service.getIncomeInDateRange(from, to);
	}
	
	@GetMapping("/getIncomeByCarInDateRange")
	double getIncomeByCarInDateRange(String idCar, String from, String to) {
		return service.getIncomeByCarInDateRange(idCar, from, to);
	}
	
	@GetMapping("/getIncomeByModelInDateRange")
	double getIncomeByModelInDateRange(String idModel, String from, String to) {
		return service.getIncomeByModelInDateRange(idModel, from, to);
	}
	
	@GetMapping("/getMostUsedCars")
	List<StringNumber> getMostUsedCars(){
		return service.getMostUsedCars();
	}
	
	@GetMapping("/getMostSaledCars")
	List<StringNumber> getMostSaledCars(){
		return service.getMostSaledCars();
	}
	
	@GetMapping("/getMostUsedModels")
	List<StringNumber> getMostUsedModels(){
		return service.getMostUsedCars();
	}
	
	@GetMapping("/getMostSaledModels")
	List<StringNumber> getMostSaledModels(){
		return service.getMostSaledModels();
	}
	
	@GetMapping("/getMostPayDrivers")
	List<StringNumber> getMostPayDrivers(){
		return service.getMostPayDrivers();
	}
	
	@GetMapping("/getMostSloopyDriver")
	List<StringNumber> getMostSloopyDrivers(){
		return service.getMostSloopyDrivers();
	}
	
	@GetMapping("/getAllModels")
	List<ModelDto> getAllModels(){
		return service.getAllModels();
	}
	
	@GetMapping("/getModelsEmpty")
	List<ModelDto> getModelsEmpty(){
		return service.getModelsEmpty();
	}

}
