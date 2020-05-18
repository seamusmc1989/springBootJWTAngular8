package com.angularBootRef.springBootPortfolio.service;

import com.angularBootRef.springBootPortfolio.domain.Car;
import com.angularBootRef.springBootPortfolio.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {

//	@Autowired
	private CarRepository carRepository;

	public String hello() {
		return "hello from api";
	}

	public List<Car> list() {
		return this.carRepository.findAll();
	}

	public Car save(Car car) {
		log.info("doc in service is: " + car.toString());
		return this.carRepository.save(car);
	}

	public Car update(Car Car) {
		this.carRepository.save(Car);
		return Car;
	}
	
	public Optional<Car> findById(Long carId) {
		log.info("findybyid  " + carId );

//		Car cartemp;
//		cartemp = new Car();
//		cartemp.setId(33l);
//		cartemp.setMake("BMW");
//		cartemp.setModel("3 service");
//		cartemp.setTransmission("auto");
//		Optional<Car> optionalCar = Optional.of(cartemp);
		Optional<Car> byId = this.carRepository.findById(carId);
//		log.info("Review just car is.. " + byId.get());

//		log.info("Review tostring is.. " + byId.get().getReview().toString());

		return byId;
	}

	public List<Car> findByOwnerId(Long ownerId) {

		log.info("findByOwnerI.  logger info for argument " + ownerId );
		return null;
//		return this.carRepository.findCarsByOwnerId(ownerId);
	}



}
