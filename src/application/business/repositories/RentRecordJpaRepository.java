package application.business.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;

import application.business.entities.Car;
import application.business.entities.RentRecord;
import application.common.CustomizedJpaRepository;

public interface RentRecordJpaRepository extends CustomizedJpaRepository<RentRecord, Integer>{
	
	@Override
	default String type() {return "Record";}
	
	List<RentRecord> findByCarAndRentDateBetween(Car car, LocalDate from, LocalDate to);
	
	@Query("SELECT r FROM RentRecord r WHERE NOT (r.returnDate = NULL) AND r.driver.idDriver = ?1 AND r.returnDate > r.plannedReturnDate")
	List<RentRecord> findByDriverDelayed(String idDriver);

	
	// records
	
	List<RentRecord> findByReturnDateIsNull();
	List<RentRecord> findByRecordClosedFalse();
	List<RentRecord> findByRentDateBetween(LocalDate from, LocalDate to);
	
	@Query("SELECT r FROM RentRecord r WHERE r.damagesRepairPrice > 0")
	List<RentRecord> findRecordsDamagedInDateRange(LocalDate from, LocalDate to);
	
	@Query("SELECT SUM(r.returnDate - r.plannedReturnDate) FROM RentRecord r WHERE r.recordClosed = true AND r.returnDate > r.plannedReturnDate AND (r.rentDate BETWEEN ?1 AND ?2)")
	Optional<Double> getTotalDelayInDateRange(LocalDate from, LocalDate to);
	
	@Query("SELECT SUM((1 - r.fuelInTankPercent/100)*r.car.model.tankVolume) FROM RentRecord r WHERE r.recordClosed = true AND (r.rentDate BETWEEN ?1 AND ?2)")
	Optional<Double> getTotalFuelFeesInDateRange(LocalDate from, LocalDate to);
	
	@Query("SELECT SUM(r.damagesRepairPrice) FROM RentRecord r WHERE r.recordClosed = true AND r.damagesRepairPrice > 0 AND r.returnDate > r.plannedReturnDate AND (r.rentDate BETWEEN ?1 AND ?2)")
	Optional<Double> getTotalDamagesRepairPriceInDateRange(LocalDate from, LocalDate to);
	
	@Query("SELECT r FROM RentRecord r WHERE NOT (r.returnDate = NULL) AND r.returnDate > r.plannedReturnDate AND (r.rentDate BETWEEN ?1 AND ?2)")
	List<RentRecord> findRecordsDelayedInRentDateRange(LocalDate from, LocalDate to);
	
	@Query("SELECT r.car.model FROM RentRecord r WHERE r.car.model.idModel= ?1 AND (r.rentDate BETWEEN ?2 AND ?3)")
	List<RentRecord> findByModelInDateRange(String idModel, LocalDate from, LocalDate to);
	
	@Query("SELECT r FROM RentRecord r WHERE r.driver.idDriver = ?1 AND (r.rentDate BETWEEN ?2 AND ?3)")
	List<RentRecord> findByDriverInDateRange(String idDriver, LocalDate from, LocalDate to);
	
	@Query("SELECT r FROM RentRecord r WHERE r.car.idCar = ?1 AND (r.rentDate BETWEEN ?2 AND ?3)")
	List<RentRecord> findByCarInDateRange(String idDriver, LocalDate start, LocalDate finish);
	
	//total
	
	@Query("SELECT SUM(r.total) FROM RentRecord r WHERE r.recordClosed = true AND (r.rentDate BETWEEN ?1 AND ?2)")
	Optional<Double> getTotalInDateRange(LocalDate from, LocalDate to);
	
	@Query("SELECT SUM(r.total) FROM RentRecord r WHERE r.recordClosed=true AND r.driver.idDriver = ?1 AND (r.rentDate BETWEEN ?2 AND ?3)")
	Optional<Double> getTotalByDriverInDateRange(String idDriver, LocalDate from, LocalDate to);
	
	@Query("SELECT SUM(r.total) FROM RentRecord r WHERE r.recordClosed=true AND r.car.idCar = ?1 AND (r.rentDate BETWEEN ?2 AND ?3)")
	Optional<Double> getTotalByCarInDateRange(String idCar, LocalDate from, LocalDate to);
	
	@Query("SELECT SUM(r.total) FROM RentRecord r WHERE r.recordClosed=true AND r.car.model.idModel = ?1 AND (r.rentDate BETWEEN ?2 AND ?3)")
	Optional<Double> getTotalByModelInDateRange(String idModel, LocalDate from, LocalDate to);
	
	// income
	
	@Query("SELECT SUM(r.total - r.damagesRepairPrice), SUM((1 - r.fuelInTankPercent/100)*r.car.model.tankVolume) "
			+ "FROM RentRecord r WHERE r.recordClosed = true AND (r.rentDate BETWEEN ?1 AND ?2)")
	Tuple getIncomeInDateRange(LocalDate from, LocalDate to);
	
	@Query("SELECT SUM(r.total - r.damagesRepairPrice), SUM((1 - r.fuelInTankPercent/100)*r.car.model.tankVolume) "
			+ "FROM RentRecord r WHERE r.car.idCar = ?1 AND r.recordClosed = true AND (r.rentDate BETWEEN ?2 AND ?3)")
	Tuple getIncomeByCarInDateRange(String idCar, LocalDate from, LocalDate to);
	
	@Query("SELECT SUM(r.total - r.damagesRepairPrice), SUM((1 - r.fuelInTankPercent/100)*r.car.model.tankVolume) "
			+ "FROM RentRecord r WHERE r.car.model.idModel = ?1 AND  r.recordClosed = true AND (r.rentDate BETWEEN ?2 AND ?3)")
	Tuple getIncomeByModelInDateRange(String idModel, LocalDate from, LocalDate to);
	
	// most
	
	@Query("SELECT r.car.model.idModel, COUNT(*) FROM RentRecord r GROUP BY r.car.model ORDER BY COUNT(*) DESC")
	List<Tuple> getMostUsedModels();
	
	@Query("SELECT r.car.model.idModel, SUM(r.total) FROM RentRecord r WHERE r.recordClosed = 'true' GROUP BY r.car.model ORDER BY SUM(r.total)  DESC")
	List<Tuple> getMostSaledModels();
	
	@Query("SELECT r.car.idCar, COUNT(*) FROM RentRecord r GROUP BY r.car ORDER BY COUNT(*)  DESC")
	List<Tuple> getMostUsedCars();
	
	@Query("SELECT r.car.idCar, SUM(r.total) FROM RentRecord r WHERE r.recordClosed = true GROUP BY r.car ORDER BY SUM(r.total)  DESC")
	List<Tuple> getMostSaledCars();
	
	@Query("SELECT r.driver.idDriver, SUM(r.total) FROM RentRecord r WHERE r.recordClosed=true GROUP BY r.driver ORDER BY SUM(r.total) DESC")
	List<Tuple> getMostPayDrivers();
	
	@Query("SELECT r.driver.idDriver, SUM(r.returnDate - r.plannedReturnDate) FROM RentRecord r WHERE NOT(r.returnDate = NULL) AND (r.returnDate > r.plannedReturnDate) GROUP BY r.driver ORDER BY SUM(r.returnDate - r.plannedReturnDate) DESC")
	List<Tuple> getMostSloopyDrivers();
	
	
	
}
