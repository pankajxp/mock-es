//package com.example.cmp;
//
//import com.example.cmp.service.CMPRetailService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.hamcrest.core.StringContains.containsString;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest()
//@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@EnableWebMvc
//@Transactional
//public class CmpApplicationTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@InjectMocks
//	private final CMPRetailService service = new CMPRetailService();
//
//	@Test
//	@DisplayName("GET /customer - Success")
//	public void subGetAllTest() throws Exception {
//
//		RequestBuilder request = MockMvcRequestBuilders.get("/customer")
//				.accept(MediaType.APPLICATION_JSON);
//
//		mockMvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().json("[{customerID: 123, lastName: Ahmed, firstName: Humaira, sortCode: '901290', accountNumber: 16857885, cityAddress: Leicester}, " +
//						"{customerID: 124, lastName: Smith, firstName: John, sortCode: '72635', accountNumber: 12345678, cityAddress: London}, " +
//						"{customerID: 125, lastName: Adam}, {customerID: 126, lastName: Jane} ]"))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("GET /customer/123 - Success")
//	public void subGetTest() throws Exception {
//
//		RequestBuilder request = MockMvcRequestBuilders.get("/customer/123")
//				.accept(MediaType.APPLICATION_JSON);
//
//		mockMvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().json("{customerID: 123, lastName: Ahmed, firstName: Humaira, sortCode: '901290', accountNumber: 16857885, cityAddress: Leicester}"))
//				.andDo(print())
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("GET /customer/5 - Fail")
//	public void subGetNegTest() throws Exception {
//
//		RequestBuilder request = MockMvcRequestBuilders.get("/customer/5")
//				.accept(MediaType.APPLICATION_JSON);
//
//		mockMvc.perform(request)
//				.andExpect(status().isNotFound())
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("POST /customer - Success")
//	public void subPostTest() throws Exception {
//
//		RequestBuilder request = MockMvcRequestBuilders.post("/customer")
//				.accept(MediaType.APPLICATION_JSON)
//				.content("{\"lastName\":\"Adams\",\"firstName\":\"Ryan\" ,\"sortCode\": \"543221\", \"accountNumber\": 87654321, \"cityAddress\":\"Manchester\"}")
//				.contentType(MediaType.APPLICATION_JSON);
//
//		mockMvc.perform(request)
//				.andExpect(status().isCreated())
//				.andExpect(header()
//						.string("location", containsString("/customer/")))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("PUT /customer/126 - Success")
//	public void subPutTest() throws Exception {
//
//		RequestBuilder request = MockMvcRequestBuilders.put("/customer/126")
//				.accept(MediaType.APPLICATION_JSON)
//				.content("{\"lastName\":\"Jane\",\"firstName\":\"Sarah\" ,\"sortCode\":\"918371\", \"accountNumber\": 10284639, \"cityAddress\":\"NewYork\"}")
//				.contentType(MediaType.APPLICATION_JSON);
//
//		mockMvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().json("{customerID: 126, lastName: Jane, firstName: Sarah, sortCode: '918371', accountNumber: 10284639, cityAddress: NewYork}"))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("PUT /customer/5 - Fail")
//	public void subPutNegTest() throws Exception {
//
//		RequestBuilder request = MockMvcRequestBuilders.put("/customer/5")
//				.content("{\"lastName\":\"Smith\",\"firstName\":\"John\" ,\"sortCode\": 12345, \"accountNumber\": 12345678, \"cityAddress\":\"Nottingham\"}")
//				.contentType(MediaType.APPLICATION_JSON);
//
//		mockMvc.perform(request)
//				.andExpect(status().isNotFound())
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("DELETE /customer/125 - Success")
//	public void subDeleteTest() throws Exception {
//
//		RequestBuilder request = MockMvcRequestBuilders.delete("/customer/125")
//				.accept(MediaType.APPLICATION_JSON);
//
//		mockMvc.perform(request)
//				.andExpect(status().is(204))
//				.andExpect(content().string(""))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("DELETE /customer/5 - Fail")
//	public void subDeleteNegTest() throws Exception {
//
//		RequestBuilder request = MockMvcRequestBuilders.delete("/customer/5")
//				.accept(MediaType.APPLICATION_JSON);
//
//		mockMvc.perform(request)
//				.andExpect(status().isNotFound())
//				.andReturn();
//	}
//
//
//}
