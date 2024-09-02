package com.example.Crud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Crud.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    // If you want to find by id (assuming id is a Long)
    // Note: JpaRepository already provides a findById method, so this is not necessary unless you need custom behavior
    // Optional<Customer> findById(Long id);

    // Custom method if you need to query by a different field
    // Customer findByCustomerName(String customerName);
}
