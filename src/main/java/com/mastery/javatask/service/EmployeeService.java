package com.mastery.javatask.service;

import com.mastery.javatask.dao.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }
}
