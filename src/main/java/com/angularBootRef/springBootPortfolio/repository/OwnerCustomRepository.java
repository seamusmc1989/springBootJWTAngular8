package com.angularBootRef.springBootPortfolio.repository;

import com.angularBootRef.springBootPortfolio.domain.Owner;
import com.angularBootRef.springBootPortfolio.domain.OwnerCarInfo;
import com.angularBootRef.springBootPortfolio.domain.OwnerCarInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerCustomRepository {
    
    List<Owner> findAllOwnersCriteria();
    Owner findOwnerByOwnerId(Long ownerId);
    List<Owner> findAllOwnersByCarIdCriteria(Long carId);
    String findFirstNameByOwnerId(Long ownerId);
    OwnerCarInfo findOwnerCarInfoByOwnerIdCriteria(Long ownerId);
    OwnerCarInfoDto findOwnerCarInfoByOwnerId(String carMake);
    void findMultiSelectByOwnerId(Long ownerId);
    void updateOwnerUsername(String oldUsername, String newUsername);
    void deleteOwnerUsername(String username);
    void updateOwnerUsernameByCarId(Long carId);
    void updateOwnerUsernameByCarMake(String carMake, String userUpdateName);

}
