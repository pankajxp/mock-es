package com.example.cs.dao;

import com.example.cs.model.RetailCustomer;
import org.springframework.data.repository.CrudRepository;

public interface RetailRepository extends CrudRepository<RetailCustomer, Long> {

}
