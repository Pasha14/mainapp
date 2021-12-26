package com.mastery.javatask.rest;

import com.mastery.javatask.dto.Employee;
import com.mastery.javatask.rest.requests.EmployeeCreateRequest;
import com.mastery.javatask.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll(){
        return employeeService.getEmployeeRepository().findAll();
    }

    @GetMapping("/{employeeId}")
    public Employee findOne(@PathVariable Long employeeId){
        return employeeService.getEmployeeRepository().findOne(employeeId);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeCreateRequest request){
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(request.getFirstName());
        newEmployee.setLastName(request.getLastName());
        newEmployee.setDepartmentId(request.getDepartmentId());
        newEmployee.setJobTitle(request.getJobTitle());
        newEmployee.setGender(request.getGender());
        newEmployee.setDateOfBirth(request.getDateOfBirth());
        return employeeService.getEmployeeRepository().save(newEmployee);
    }

    @PutMapping("/update/{employeeId}")
    public Employee updateEmployee(@RequestBody EmployeeCreateRequest request, @PathVariable Long employeeId){
        Employee updateEmployee = employeeService.getEmployeeRepository().findOne(employeeId);
        updateEmployee.setFirstName(request.getFirstName());
        updateEmployee.setLastName(request.getLastName());
        updateEmployee.setDepartmentId(request.getDepartmentId());
        updateEmployee.setJobTitle(request.getJobTitle());
        updateEmployee.setGender(request.getGender());
        updateEmployee.setDateOfBirth(request.getDateOfBirth());
        return employeeService.getEmployeeRepository().update(updateEmployee);
    }

    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId){
        employeeService.getEmployeeRepository().delete(employeeId);
    }

}
