package com.angularBootRef.springBootPortfolio;
import com.angularBootRef.springBootPortfolio.controller.CarController;
import com.angularBootRef.springBootPortfolio.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleTest {

    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .build();
    }

    @Test
    public void testCarServiceHello() throws Exception {

        when(carService.hello()).thenReturn("hello");
        mockMvc.perform(get("/api/car/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
        verify(carService).hello();
    }


}
