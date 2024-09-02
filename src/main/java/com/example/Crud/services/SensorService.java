package com.example.Crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Crud.repos.CustomerRepo;
import com.example.Crud.repos.SensorRepo;


@Service
public class SensorService {   

    @Autowired
    private SensorRepo sensorRepo;

    @Transactional
    public void deleteByCustomerId(Long id) {
        sensorRepo.deleteByCustomerId(id.intValue());
    }
}
