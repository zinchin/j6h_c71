package application.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import application.business.config.RccConfig;
import application.business.dto.CarDto;
import application.business.dto.BusinessDtoService;
import application.business.dto.ModelDto;
import application.business.dto.RccConfigDto;
import application.business.entities.Car;
import application.business.entities.CarState;
import application.business.entities.Model;
import application.business.repositories.CarJpaRepository;
import application.business.repositories.ModelJpaRepository;
import application.business.repositories.RccConfigJpaRepository;

@Service
@Transactional
public class BossServiceImpl implements BossService {
	
	@Autowired RccConfigJpaRepository configRepo;
	@Autowired ModelJpaRepository modelRepo;
	@Autowired CarJpaRepository carRepo;
	
	@Autowired BusinessDtoService dtoService;
	
	@Override
	public RccConfigDto setConfig(RccConfigDto config) {	
		RccConfig rccConfig = dtoService.getRccConfig(config);
		configRepo.save(rccConfig);
		return config;	
	}
	
	@Override
	public ModelDto addModel(ModelDto modelDto) {
		modelRepo.checkById(modelDto.getIdModel(), false);
		modelRepo.save(dtoService.getModel(modelDto));
		return modelDto;
	}
	
	@Override
	public ModelDto setModelDailyRate(String idModel, double dailyRate) {
		Model model = modelRepo.checkById(idModel, true);
		model.setDailyRate(dailyRate);
		return dtoService.getModelDto(model);
	}
	
	@Override
	public CarDto setCarWrittenOff(String idCar) {
		Car car = carRepo.checkById(idCar, true);
		car.setState(CarState.WRITTEN_OFF);
		return dtoService.getCarDto(car);
	}

}
