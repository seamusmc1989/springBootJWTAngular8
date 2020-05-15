package com.angularBootRef.springBootPortfolio.controller;


import com.angularBootRef.springBootPortfolio.converter.CarDtoConverter;
import com.angularBootRef.springBootPortfolio.domain.Car;
import com.angularBootRef.springBootPortfolio.dto.CarDto;
import com.angularBootRef.springBootPortfolio.service.CarService;
import com.angularBootRef.springBootPortfolio.service.CarTestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/car")
@RestController
@AllArgsConstructor
public class CarController {

	private CarTestService carTestService;

	private CarService carService;

	private CarDtoConverter carDtoConverter;

	@PostMapping("/save")
    public CarDto saveCar(@RequestBody @Valid Car car) {
		log.info("car is: " + car.toString());
		final Car result = this.carService.save(car);
		return this.carDtoConverter.convertToDto(result);
    }

	@GetMapping("/hello")
	public String hello() {
		return this.carService.hello();
	}

	@GetMapping("/list")
	public List<CarDto> findAll() {
		final List<Car> result = this.carService.list();
		return this.carDtoConverter.convertToDto(result);
	}

	@GetMapping("/list/nonDto")
	public List<Car> findAllCar() {
		final List<Car> result = this.carService.list();
		return result;
	}

	@GetMapping("/findById/{carId}")
	public CarDto findCarPathById(@PathVariable Long carId) {
//		log.info("carId fetched is: " + carId);

		final Car result = this.carService.findById(carId).get();
//		log.info("result.  logger info for argument " + result.toString() );
		return this.carDtoConverter.convertToDto(result);
	}

	@GetMapping("/findByOwnerId")
	public List<CarDto> findCarsByOwnerId() {

		List<CarDto> carDtos = new ArrayList<>();
		return carDtos;
//		final List<Car> result = this.carService.findByOwnerId(1l);
//		log.info("findCarsByOwnerId: " + result.toString());
//		return this.carDtoConverter.convertToDto(result);
	}




}
