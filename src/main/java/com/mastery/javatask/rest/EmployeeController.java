package com.mastery.javatask.rest;

import com.mastery.javatask.dto.Employee;
import com.mastery.javatask.rest.requests.EmployeeCreateRequest;
import com.mastery.javatask.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Api(description = "employees controller", tags = { "Employees" })
public class EmployeeController {

    private final EmployeeService employeeService;

    @ApiOperation(value = "Get all employees")
    @GetMapping
    public List<Employee> findAll(){
        return employeeService.getEmployeeRepository().findAll();
    }

    @ApiOperation(value = "Find employee by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", dataType = "integer", paramType = "path", value = "Id of employee")
    })
    @GetMapping("/{employeeId}")
    public Employee findOne(@PathVariable Long employeeId){
        return employeeService.getEmployeeRepository().findOne(employeeId);
    }

    @ApiOperation(value = "Create new employee")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", dataType = "Employee", paramType = "body", value = "New employee")
    })
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

    @ApiOperation(value = "Update employee")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", dataType = "integer", paramType = "path", value = "Id of employee"),
            @ApiImplicitParam(name = "request", dataType = "Employee", paramType = "body", value = "New employee")
    })
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

    @ApiOperation(value = "Delete employee")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", dataType = "integer", paramType = "path", value = "Id of employee")
    })
    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId){
        employeeService.getEmployeeRepository().delete(employeeId);
    }

    @ApiOperation(value = "Error handling demo")
    @PostMapping("/demo")
    public Employee generateOne(){
        throw new RuntimeException("Error handling demo");
    }

}
