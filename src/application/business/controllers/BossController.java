package application.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.CarDto;
import application.business.dto.ModelDto;
import application.business.dto.RccConfigDto;
import application.business.services.BossService;

@RestController
@CrossOrigin
@RequestMapping("/boss")
public class BossController {
	
	@Autowired BossService service;
	
	@PostMapping("/setConfig")
	public RccConfigDto setConfig(@RequestBody RccConfigDto config) {
		return service.setConfig(config);
	}
	
	@PostMapping("/addModel")
	public ModelDto addModel(@RequestBody ModelDto model) {
		return service.addModel(model);
	}
	
	@PutMapping("/setDailyRate")
	public ModelDto setDailyRate(String idModel, double dailyRate) {
		return service.setModelDailyRate(idModel, dailyRate);
	}
	
	@PutMapping("/setCarWrittenOff")
	public CarDto setCarWrittenOff(String idCar) {
		return service.setCarWrittenOff(idCar);
	}

}
