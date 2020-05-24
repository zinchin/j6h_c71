package application.business.repositories;

import application.business.entities.Driver;
import application.common.CustomizedJpaRepository;

public interface DriverJpaRepository extends CustomizedJpaRepository<Driver, String>{
	
	@Override
	default String type() {return "Driver";}

}
