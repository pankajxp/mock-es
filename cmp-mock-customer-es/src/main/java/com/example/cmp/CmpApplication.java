package com.example.cmp;

import com.example.cmp.dao.CMPRetailRepository;
import com.example.cmp.model.CMPRetailCustomer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmpApplication.class, args);
	}

	@Bean
	CommandLineRunner init(CMPRetailRepository cmpRetailRepository){
		return args -> {
			List<CMPRetailCustomer> customers = new ArrayList<>(Arrays.asList(
            new CMPRetailCustomer(1L, "Humaira", false),
            new CMPRetailCustomer(2L, "Pankaj", false),
            new CMPRetailCustomer(3L, "John", true)
    		));
			customers.forEach(customer -> cmpRetailRepository.save(new CMPRetailCustomer(customer)));
		};
	}

}
