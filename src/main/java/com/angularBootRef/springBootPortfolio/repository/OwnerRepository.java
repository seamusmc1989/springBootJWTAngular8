package com.angularBootRef.springBootPortfolio.repository;

import com.angularBootRef.springBootPortfolio.domain.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long>{



//    @Query("select c from Car c where c.carId = :ownerId")
//    @Query("select c from Car c join c.carId o where o.owner_Id = :ownerId")
//    @Query("select c from car c INNER JOIN owner o ON o.car_id = c.car_id where o.owner_id = :ownerId")
//    @Query("from Review r inner join fetch r.comments where r.reviewId = :id")
//    @Query("from Owner o where o.car = :carId")
//    List<Owner> findByCarId(@Param("carId") Car carId);
//      List<Owner> findByCar(Car car);
//      List<Owner> findByFirstName(String firstname);

    List<Owner> findAllByCar_Id(Long carId);

//      List<Owner> findBycar_carId(Long carId);

}
