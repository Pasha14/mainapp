package com.mastery.javatask.rest.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeCreateRequest {

    private String firstName;
    private String lastName;
    private Long departmentId;
    private String jobTitle;
    private String gender;
    private Date dateOfBirth;

}
