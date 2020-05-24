package application.business.repositories;

import java.util.List;

import application.business.entities.Car;
import application.business.entities.CarState;
import application.business.entities.Model;
import application.common.CustomizedJpaRepository;

public interface CarJpaRepository extends CustomizedJpaRepository<Car,String>{
	
	@Override
	default String type() {return "Car";}

	List<Car> findByStateEquals(CarState state);
	List<Car> findByModelEqualsAndStateEquals(Model model, CarState state);
	List<Car> findByModelEquals(Model model);
	
}
