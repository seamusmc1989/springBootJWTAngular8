package com.angularBootRef.springBootPortfolio;

import com.angularBootRef.springBootPortfolio.domain.Car;
import com.angularBootRef.springBootPortfolio.repository.CarRepository;
import com.angularBootRef.springBootPortfolio.service.CarService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import utils.H2JpaConfig;
import utils.JsonMapper;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootPortfolioApplication.class, H2JpaConfig.class, SecurityContext.class})
@AutoConfigureMockMvc
public class CarIntegrationTest {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MockMvc mockMvc;

    private JsonMapper<Car> jsonMapper = new JsonMapper();

    @Test
    @WithMockUser(username = "username", roles = {"USER", "ADMIN"})
    public void saveCarAPIToDb() throws Exception {

        Car firstCar = new Car();
        firstCar.setId(1l);
        firstCar.setMake("AUDI");
        firstCar.setModel("Quatro");
        firstCar.setTransmission("auto");
        firstCar.setEngine("diesel");

        final String content = this.jsonMapper.fromObjectToJson(firstCar);

        this.mockMvc.perform(post("/api/car/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk());

        Car secondCar = new Car();
        secondCar.setId(4l);
        secondCar.setMake("NEWWUDI");
        secondCar.setModel("Quatro");
        secondCar.setTransmission("auto");
        secondCar.setEngine("diesel");

        final String content2 = this.jsonMapper.fromObjectToJson(secondCar);

        this.mockMvc.perform(post("/api/car/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content2))
                .andExpect(status().isOk());

        final MvcResult result = this.mockMvc.perform(get("/api/car/list")).andExpect(status().isOk()).andReturn();

        final List<Car> carsFromDb = this.jsonMapper.fromJsonArrayToObjectList(result.getResponse().getContentAsString(), Car.class);
        Assert.assertEquals(2, carsFromDb.size());

        Assert.assertEquals(firstCar.getId(), carsFromDb.get(0).getId());
        Assert.assertEquals(firstCar.getMake(), carsFromDb.get(0).getMake());
        Assert.assertEquals(firstCar.getModel(), carsFromDb.get(0).getModel());
        Assert.assertEquals(firstCar.getTransmission(), carsFromDb.get(0).getTransmission());

    }

    @Test
    @WithMockUser(username = "username", roles = {"USER", "ADMIN"})
    public void saveCarRepositoryToDb() throws Exception {

        Car car = new Car();
        car.setId(1l);
        car.setMake("AUDI");
        car.setModel("Quatro");
        car.setTransmission("auto");
        car.setEngine("diesel");

        this.carRepository.save(car);

        final MvcResult result = this.mockMvc.perform(get("/api/car/list")).andExpect(status().isOk()).andReturn();
        List<Car> carsFromDb = this.jsonMapper.fromJsonArrayToObjectList(result.getResponse().getContentAsString(), Car.class);

        System.out.println("info for argument " + carsFromDb.size() );
        Assert.assertEquals(1, carsFromDb.size());
        Assert.assertEquals(car.getId(), carsFromDb.get(0).getId());
        Assert.assertEquals(car.getMake(), carsFromDb.get(0).getMake());
        Assert.assertEquals(car.getModel(), carsFromDb.get(0).getModel());
        Assert.assertEquals(car.getTransmission(), carsFromDb.get(0).getTransmission());

    }

    @Test
    @WithMockUser(username = "username", roles = {"USER", "ADMIN"})
    public void saveCarServiceToDb() throws Exception {

        Car car = new Car();
        car.setId(1l);
        car.setMake("AUDI");
        car.setModel("Quatro");
        car.setTransmission("auto");
        car.setEngine("diesel");

        this.carService.save(car);

        final MvcResult result = this.mockMvc.perform(get("/api/car/list")).andExpect(status().isOk()).andReturn();
        List<Car> carsFromDb = this.jsonMapper.fromJsonArrayToObjectList(result.getResponse().getContentAsString(), Car.class);

        System.out.println("info for argument " + carsFromDb.size() );
        Assert.assertEquals(1, carsFromDb.size());
        Assert.assertEquals(car.getId(), carsFromDb.get(0).getId());
        Assert.assertEquals(car.getMake(), carsFromDb.get(0).getMake());
        Assert.assertEquals(car.getModel(), carsFromDb.get(0).getModel());
        Assert.assertEquals(car.getTransmission(), carsFromDb.get(0).getTransmission());

    }

    //fetch car by id...
    //fetch car by ownerid..

    @After
    public void after() {
        this.carRepository.deleteAll();
    }
}
