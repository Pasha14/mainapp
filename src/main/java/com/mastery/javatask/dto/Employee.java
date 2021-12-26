package com.mastery.javatask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private Long departmentId;
    private String jobTitle;
    private String gender;
    private Date dateOfBirth;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
