package com.angularBootRef.springBootPortfolio.converter;

import com.angularBootRef.springBootPortfolio.domain.Car;
import com.angularBootRef.springBootPortfolio.dto.CarDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CarDtoConverter implements IObjectDtoConverter<CarDto, Car> {
    @Override
    public CarDto convertToDto(Car src) {
        final CarDto carDto = new CarDto();
        carDto.setId(src.getId());
        carDto.setMake(src.getMake());
        carDto.setModel(src.getModel());
        carDto.setEngine(src.getEngine());
        carDto.setTransmission(src.getTransmission());
        carDto.setUsername(src.getUsername());

        return carDto;
    }

    @Override
    public Car convertFromDto(CarDto src) {
        final Car car = new Car();
        car.setId(src.getId());
        car.setMake(src.getMake());
        car.setModel(src.getModel());
        car.setEngine(src.getEngine());
        car.setTransmission(src.getTransmission());
        car.setUsername(src.getUsername());

        return car;
    }
}
