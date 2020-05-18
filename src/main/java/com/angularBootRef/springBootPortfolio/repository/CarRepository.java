package com.angularBootRef.springBootPortfolio.repository;

import com.angularBootRef.springBootPortfolio.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
