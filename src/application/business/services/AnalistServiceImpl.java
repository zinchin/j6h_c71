package application.business.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import application.business.dto.CarDto;
import application.business.dto.BusinessDtoService;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;
import application.business.entities.CarState;
import application.business.entities.Model;
import application.business.repositories.CarJpaRepository;
import application.business.repositories.DriverJpaRepository;
import application.business.repositories.ModelJpaRepository;
import application.business.repositories.RccConfigJpaRepository;
import application.business.repositories.RentRecordJpaRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Transactional(readOnly = true)
public class AnalistServiceImpl implements AnalistService {

	@Autowired CarJpaRepository carRepo;
	@Autowired ModelJpaRepository modelRepo;
	@Autowired DriverJpaRepository driverRepo;
	@Autowired RentRecordJpaRepository recordRepo;
	@Autowired BusinessDtoService dtoService;
	@Autowired RccConfigJpaRepository configRepo;
	@Autowired DateTimeFormatter dtf;
	
//	@NoArgsConstructor
//	@AllArgsConstructor
//	@Data
//	public class StringNumber{
//		
//		String name;
//		Number value;
//			
//	}
		
	@Override
	public List<CarDto> getAllCars(){
		return dtoService.collectionToList(carRepo.findAll(), dtoService.carDto);
	}
		
	@Override
	public List<CarDto> getCarsByModel(String idModel){
		return dtoService.collectionToList(modelRepo.checkById(idModel, true).getCars(), dtoService.carDto);
	}
	
	// records
	
	
	
	@Override
	public RentRecordDto getRecord(int idRecord) {
		return dtoService.getRentRecordDto(recordRepo.checkById(idRecord, true));
	}
	
	
	@Override
	public List<RentRecordDto> getRecordsInDateRange(String from, String to){
		return dtoService.collectionToList(recordRepo.findByRentDateBetween(LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)), dtoService.recordDto);
	}
	
	
	@Override
	public List<RentRecordDto> getAllRecordsUnreceived(){
		return dtoService.collectionToList(recordRepo.findByReturnDateIsNull(), dtoService.recordDto);
	}
	
	
	@Override
	public List<RentRecordDto> getAllRecordsUnclosed(){
		return dtoService.collectionToList(recordRepo.findByRecordClosedFalse(), dtoService.recordDto);
	}
	
	
	@Override
	public List<RentRecordDto> getRecordsDelayedInDateRange(String from, String to){
		return dtoService.collectionToList(recordRepo.findRecordsDelayedInRentDateRange(LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)), dtoService.recordDto);
	}
	
	
	@Override
	public List<RentRecordDto> getRecordsDamagedInDateRange(String from, String to){
		return dtoService.collectionToList(recordRepo.findRecordsDamagedInDateRange(LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)), dtoService.recordDto);
	} 
	
	
	@Override
	public List<RentRecordDto> getRecordsByModelInDateRange(String idDriver, String from, String to){
		return dtoService.collectionToList(recordRepo.findByDriverInDateRange(idDriver, LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)), dtoService.recordDto);
	}
	
	
	@Override
	public List<RentRecordDto> getRecordsByCarInDateRange(String idCar, String from, String to){
		return dtoService.collectionToList(recordRepo.findByCarInDateRange(idCar, LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)), dtoService.recordDto);
	}
	
	
	@Override
	public List<RentRecordDto> getRecordsByDriverInDateRange(String idDriver, String from, String to){
		return dtoService.collectionToList(recordRepo.findByDriverInDateRange(idDriver, LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)), dtoService.recordDto);
	}
	
	// total
	
	@Override
	public double getTotalInDateRange(String from, String to) {
		return recordRepo.getTotalInDateRange(LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)).orElse(0.);
	}
	
	
	@Override
	public double getTotalByCarInDateRange(String idCar, String from, String to) {
		return recordRepo.getTotalByCarInDateRange(idCar, LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)).orElse(0.);
	}
	
	
	@Override
	public double getTotalByModelInDateRange(String idModel, String from, String to) {
		return recordRepo.getTotalByModelInDateRange(idModel, LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)).orElse(0.);
	}
	
	
	@Override
	public double getTotalByDriverInDateRange(String idDriver, String from, String to) {
		return recordRepo.getTotalByCarInDateRange(idDriver, LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)).orElse(0.);
	}
	
	
	@Override
	public double getTotalDelayInDateRange(String from, String to) {
		return recordRepo.getTotalDelayInDateRange(LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)).orElse(0.);
	}
	
	
	@Override
	public double getTotalDamagesRepairPriceInDateRange(String from, String to) {
		return recordRepo.getTotalDamagesRepairPriceInDateRange(LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)).orElse(0.);
	}
	
	
	@Override
	public double getTotalFuelFeesInDateRange(String from, String to) {
		return recordRepo.getTotalFuelFeesInDateRange(LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)).orElse(0.)*(configRepo.checkById(1, true).getFuelPrice());
	}
	
	//  income
	
	
	@Override
	public double getIncomeInDateRange(String from, String to) {
		return retrieveIncome (recordRepo.getIncomeInDateRange(LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)),
				configRepo.checkById(1, true).getFuelPrice());
	}
	
	
	@Override
	public double getIncomeByCarInDateRange(String idCar, String from, String to) {
		return retrieveIncome (recordRepo.getIncomeByCarInDateRange(idCar, LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)),
				configRepo.checkById(1, true).getFuelPrice());
	}
	
	
	@Override
	public double getIncomeByModelInDateRange(String idModel, String from, String to) {
		return retrieveIncome (recordRepo.getIncomeByModelInDateRange(idModel, LocalDate.parse(from, dtf), LocalDate.parse(to,dtf)),
				configRepo.checkById(1, true).getFuelPrice());
	}
	
	//most
	
	
	@Override
	public List<StringNumber> getMostUsedCars(){
		return tupleToStringNumber(recordRepo.getMostUsedCars());
	}
	
	
	@Override
	public List<StringNumber> getMostSaledCars(){
		return tupleToStringNumber(recordRepo.getMostSaledCars());
	}
	
	
	@Override
	public List<StringNumber> getMostUsedModels(){
		return tupleToStringNumber(recordRepo.getMostUsedModels());
	}
	
	
	@Override
	public List<StringNumber> getMostSaledModels(){
		return tupleToStringNumber(recordRepo.getMostSaledModels());
	}
	
	
	@Override
	public List<StringNumber> getMostPayDrivers(){
		return tupleToStringNumber(recordRepo.getMostPayDrivers());
	}
	
	
	@Override
	public List<StringNumber> getMostSloopyDrivers(){
		return tupleToStringNumber(recordRepo.getMostSloopyDrivers());
	}
	
	// misc
	
	
	@Override
	public List<ModelDto> getAllModels(){
		return dtoService.collectionToList(modelRepo.findAll(), dtoService.modelDto);
	}
	
	
	@Override
	public List<ModelDto> getModelsEmpty(){
		return dtoService.collectionToList(modelRepo.findAll()
				                           .stream()
				                           .filter(model -> isModelWrittenOffOrEmpty(model))
				                           .collect(Collectors.toList()), 
				                           dtoService.modelDto);
	}
	
	private boolean isModelWrittenOffOrEmpty(Model model) {
		return modelRepo.checkById(model.getIdModel(), true).getCars()
				.stream()
				.filter(car -> car.getState() != CarState.WRITTEN_OFF)
				.collect(Collectors.toList()).size() == 0;
	}
	
	private List<StringNumber> tupleToStringNumber(List<Tuple> tuples){
		return tuples.stream()
				.map(tuple -> new StringNumber(tuple.get(0, String.class), tuple.get(1,Number.class)))
				.collect(Collectors.toList());
	}
	
	private double retrieveIncome(Tuple tuple, double fuelPrice) {
		Double total = tuple.get(0, Double.class);
		Double fuel = tuple.get(1, Double.class);
		return (total == null ? 0. : total) - (fuel == null ? 0. : fuel)*fuelPrice;
	}

}
