package com.angularBootRef.springBootPortfolio.service;

import com.angularBootRef.springBootPortfolio.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

	List<Car> list();
	Car save(Car car);
	Car update(Car car);
	Optional<Car> findById(Long carId);
	List<Car> findByOwnerId(Long ownerId);
	String hello();
	
}
