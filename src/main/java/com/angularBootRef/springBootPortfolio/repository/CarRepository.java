package com.angularBootRef.springBootPortfolio.repository;

import com.angularBootRef.springBootPortfolio.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CarRepository extends JpaRepository<Car, Long>{ }
