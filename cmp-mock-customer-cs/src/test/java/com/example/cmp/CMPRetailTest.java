//package com.example.cmp;
//
//import com.example.cmp.controller.CMPRetailController;
//import com.example.cmp.model.CMPRetailCustomer;
//import com.example.cmp.service.CMPRetailService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//
//@WebMvcTest(controllers = CMPRetailController.class)
//@ActiveProfiles("test")
//public class CMPRetailTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CMPRetailService cmpRetailService;
//
//    private List<CMPRetailCustomer> customerList;
//
//    @BeforeEach
//    void setUp(){
//        this.customerList = new ArrayList<>();
//        this.customerList.add(new CMPRetailCustomer(123L, "Ahmed", "Humaira", "090129", 12345678, "Leicester"));
//
//    }
//
//}
