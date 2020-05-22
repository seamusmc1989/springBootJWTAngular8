package com.angularBootRef.springBootPortfolio.service;

import com.angularBootRef.springBootPortfolio.domain.Owner;
import com.angularBootRef.springBootPortfolio.domain.OwnerCarInfoDto;
import com.angularBootRef.springBootPortfolio.repository.OwnerRepository;
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
public class OwnerServiceImpl implements OwnerService {

//	@Autowired
	private OwnerRepository ownerRepository;

	public List<Owner> list() {
		return (List<Owner>) this.ownerRepository.findAll();
	}

	public Owner save(Owner owner) {
		log.info("owner in service is: " + owner.toString());
		return this.ownerRepository.save(owner);
	}

	public Owner update(Owner Owner) {
		this.ownerRepository.save(Owner);
		return Owner;
	}
	
	public Optional<Owner> findById(Long ownerId) {
		return this.ownerRepository.findById(ownerId);
	}

	public List<Owner> findByCarId(Long carId) {

		return this.ownerRepository.findAllOwnersByCarIdCriteria(carId);
//		return	this.ownerRepository.findAllByCar_Id(carId);
	}

}
