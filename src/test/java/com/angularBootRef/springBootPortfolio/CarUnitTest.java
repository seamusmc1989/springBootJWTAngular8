package com.angularBootRef.springBootPortfolio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CarUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "username", roles = {"USER", "ADMIN"})
    public void findAll() throws Exception {
        mockMvc.perform(get("/api/car/list"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.id", is(1)));

        MvcResult mvcResult = this.mockMvc.perform(get("/api/car/list")).andReturn();
        System.out.println(("mvc result is . " +  mvcResult.getResponse()));
    }

    @Test
    @WithMockUser(username = "username", roles = {"USER", "ADMIN"})
    public void findOne() throws Exception {
        mockMvc.perform(get("/api/car/list/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }



}
