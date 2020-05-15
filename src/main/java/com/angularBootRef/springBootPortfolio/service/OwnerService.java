package com.angularBootRef.springBootPortfolio.service;

import com.angularBootRef.springBootPortfolio.domain.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {

	List<Owner> list();
	Owner save(Owner owner);
	Owner update(Owner owner);
	Optional<Owner> findById(Long ownerId);
	List<Owner> findByCarId(Long carId);
}
