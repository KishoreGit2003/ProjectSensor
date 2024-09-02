package com.example.Crud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.Crud.model.Sensors;
import java.util.List;
public interface SensorRepo extends JpaRepository<Sensors, Long> {
    List<Sensors> findByCustomerId(int customerId);
    void deleteByCustomerId(int customerId);
}