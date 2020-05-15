package com.angularBootRef.springBootPortfolio;

import com.angularBootRef.springBootPortfolio.controller.CarController;
import com.angularBootRef.springBootPortfolio.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarControllerRestSimpleHello {

    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(this.carController).build();
    }

    @Test
    public void getCarHelloTest() throws Exception {
        Mockito.when(this.carService.hello()).thenReturn("hello");
        System.out.println("get All Cars");

        mockMvc.perform(get("/api/car/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));

        Mockito.verify(this.carService).hello();
    }


}

