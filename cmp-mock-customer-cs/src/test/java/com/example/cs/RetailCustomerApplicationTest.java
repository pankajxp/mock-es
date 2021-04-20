package com.example.cs;

import com.example.cs.service.RetailServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableWebMvc
@Transactional
@ContextConfiguration(classes = RetailCustomerApplication.class)
public class RetailCustomerApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private final RetailServiceImpl service = new RetailServiceImpl();

    @Test
    @DisplayName("GET /cs/customer/1 - Success")
    public void subGetTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/cs/customer/1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{csCustomerID: 1, csName: Humaira, csIsSenior: false}"))
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("GET /cs/customer/5 - Fail")
    public void subGetNegTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/cs/customer/5")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer Not Found"))

                .andReturn();
    }

    @Test
	@DisplayName("POST /cs/customer - Success")
	public void subPostTest() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/cs/customer")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"csName\":\"Ryan\",\"csIsSenior\":true }")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{csCustomerID: 4, csName: Ryan, csIsSenior: true}"))
                .andDo(print())
				.andReturn();
	}
}
