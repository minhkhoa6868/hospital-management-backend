package com.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.model.Employee;
import com.model.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private long Ecode;
    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private Gender gender;
    private LocalDate startDate;
    private String address;
    private String speName;
    private long speDegreeYear;
    private String employeeType;
    private List<String> phoneNumbers;

    public EmployeeDTO(Employee employee) {
         this.Ecode = employee.getEcode();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.DOB = employee.getDOB();
        this.gender = employee.getGender();
        this.startDate = employee.getStartDate();
        this.address = employee.getAddress();
        this.speName = employee.getSpeName();
        this.speDegreeYear = employee.getSpeDegreeYear();
        this.employeeType = employee.getEmployeeType();
        
        // Convert the phone_numbers list to a list of phone numbers (strings)
        this.phoneNumbers = employee.getPhone_numbers().stream()
                                      .map(phone -> phone.getPhoneNumber())
                                      .collect(Collectors.toList());
    }
}