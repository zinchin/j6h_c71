package application.business.repositories;

import application.business.entities.Model;
import application.common.CustomizedJpaRepository;

public interface ModelJpaRepository extends CustomizedJpaRepository<Model,String>{
	
	@Override
	default String type() {return "Model";}

}
