package com.angularBootRef.springBootPortfolio.controller;

import com.angularBootRef.springBootPortfolio.converter.OwnerDtoConverter;
import com.angularBootRef.springBootPortfolio.domain.Owner;
import com.angularBootRef.springBootPortfolio.dto.OwnerDto;
import com.angularBootRef.springBootPortfolio.service.CarService;
import com.angularBootRef.springBootPortfolio.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/owner")
@RestController
@AllArgsConstructor
public class OwnerController {

	private OwnerService ownerService;
	private OwnerDtoConverter ownerDtoConverter;
	private CarService carService;

	@PostMapping("/cars/{carId}/owners")
	public Owner createOwner(@PathVariable (value = "carId") Long carId,
                             @Valid @RequestBody Owner owner) {

		return carService.findById(carId).map(car -> {
			owner.setCar(car);
			return ownerService.save(owner);
		}).orElseThrow(() -> new ResourceNotFoundException("CarId " + carId + " not found"));
	}


	@GetMapping("/list")
	public List<OwnerDto> findAllOwners() {
		final List<Owner> result = this.ownerService.list();

		return this.ownerDtoConverter.convertToDto(result);
	}

	@GetMapping("/listOwnerByCarId/{carId}")
	public List<OwnerDto> findOwnerByCarId(@PathVariable Long carId) {
		log.info("listOwnerByCarId for carId: " + carId);
		final List<Owner> carOwners = this.ownerService.findByCarId(carId);


		return this.ownerDtoConverter.convertToDto(carOwners);
	}
}
