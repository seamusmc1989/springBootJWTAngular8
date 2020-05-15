package com.angularBootRef.springBootPortfolio;

import com.angularBootRef.springBootPortfolio.controller.CarController;
import com.angularBootRef.springBootPortfolio.converter.CarDtoConverter;
import com.angularBootRef.springBootPortfolio.domain.Car;
import com.angularBootRef.springBootPortfolio.domain.Review;
import com.angularBootRef.springBootPortfolio.dto.CarDto;
import com.angularBootRef.springBootPortfolio.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import utils.H2JpaConfig;
import utils.JsonMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {SpringBootPortfolioApplication.class, H2JpaConfig.class, SecurityContext.class})
//@AutoConfigureMockMvc
public class CarControllerRestTest {

    private JsonMapper<CarDto> jsonDtoMapper = new JsonMapper();
    private JsonMapper<Car> jsonMapper = new JsonMapper();

    private List<CarDto> carDtoList = new ArrayList<>();
    private List<Car> carList = new ArrayList<>();

    @Mock
    private CarService carService;

    @Mock
    private CarDtoConverter carDtoConverterMock;

    private MockMvc mockMvc;

    @InjectMocks
    private CarController carController;

    @Before
    public void before() {

        final Review review = new Review();
        final Car car1 = new Car(1l, "BMW", "3series", "diesel", "auto", "user1", review);
        final Car car2 = new Car(2l, "BMW", "4series", "petrol", "auto", "user1", review);
        this.carList.add(car1);
        this.carList.add(car2);

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.carController).build();
    }

    @Test
//@WithMockUser(username="username",roles={"USER","ADMIN"})
    public void getAllCarsTest() throws Exception {

        System.out.println("getAllCarsTest2: ");

        Mockito.when(this.carService.list()).thenReturn(this.carList);

        final String expected = jsonMapper.fromObjectListToJson(this.carList);

        System.out.println("expected value is: " + expected);
        this.mockMvc.perform(get("/api/car/list/nonDto"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expected, false));

        verify(this.carService).list();
    }


    @Test
    public void getAllCarsTestDto() throws Exception {

        System.out.println("getAllCarsTestDto: ");

        Mockito.when(this.carService.list()).thenReturn(this.carList);

        Mockito.when(this.carDtoConverterMock.convertToDto(this.carList)).thenReturn(this.carDtoList);
        final String expected = jsonDtoMapper.fromObjectListToJson(this.carDtoList);

        System.out.println("expected value is: " + expected);
        this.mockMvc.perform(get("/api/car/list"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected, false));

        verify(this.carService).list();
    }


//    @Test
    public void findCarByIdTest() throws Exception {
        final Car expectedCar = this.carList.get(0);
        final CarDto expectedCarDto = this.carDtoConverterMock.convertToDto(expectedCar);

            Mockito.when(this.carService.findById(1l)).thenReturn(Optional.of(expectedCar));
            Mockito.when(this.carDtoConverterMock.convertToDto(expectedCar)).thenReturn(expectedCarDto);

            final String expected = this.jsonDtoMapper.fromObjectToJson(expectedCarDto);
            this.mockMvc.perform(get("/api/car/findById/1")).andExpect(status().isOk()).andExpect(content().json(expected, false));
    }

}
