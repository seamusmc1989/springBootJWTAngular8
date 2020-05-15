package com.angularBootRef.springBootPortfolio;

import com.angularBootRef.springBootPortfolio.controller.CarController;
import com.angularBootRef.springBootPortfolio.converter.CarDtoConverter;
import com.angularBootRef.springBootPortfolio.domain.Car;
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
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import utils.JsonMapper;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootPortfolioApplication.class, CarService.class, CarController.class})
//    @SpringBootTest(classes = {SpringBootPortfolioApplication.class})
@AutoConfigureMockMvc
public class SpringBootPortfolioApplicationTests {

        private MockMvc mockMvc;
        private CarDtoConverter carDtoConverter;

        private JsonMapper<CarDto> jsonDtoMapper = new JsonMapper();
        private JsonMapper<Car> jsonMapper = new JsonMapper();

        private List<CarDto> carDtoList = new ArrayList<>();
        private List<Car> carList = new ArrayList<>();

        @Mock
        private CarService carService;

        @Mock
        private CarDtoConverter carDtoConverterMock;

        @InjectMocks
        private CarController carController;

        @Before
        public void before() {
            MockitoAnnotations.initMocks(this);
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.carController).build();
        }

        @Test
        public void findAllTest() throws Exception {

            System.out.println("find All Tests");

            Mockito.when(this.carService.list()).thenReturn(this.carList);
            Mockito.when(this.carDtoConverterMock.convertToDto(this.carList)).thenReturn(this.carDtoList);
            final String expected = jsonDtoMapper.fromObjectListToJson(this.carDtoList);

            this.mockMvc.perform(get("/api/car/list")).andExpect(status().isOk()).andExpect(content().json(expected, false));
        }


    }
