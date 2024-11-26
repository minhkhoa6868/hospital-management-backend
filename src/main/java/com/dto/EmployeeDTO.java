package com.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.model.Employee;
import com.model.Employee_type;
import com.model.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private Long Ecode;
    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private Gender gender;
    private LocalDate startDate;
    private String address;
    private String speName;
    private long speDegreeYear;
    private Employee_type employeeType;
    private List<String> phoneNumbers;
    private String deptTitle;
    private List<Long> examinations;

    public EmployeeDTO() {
        // Default constructor for deserialization
    }

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
        if (employee.getPhone_numbers() == null) {
            this.phoneNumbers = null;
        } else {
            this.phoneNumbers = employee.getPhone_numbers().stream()
                    .map(phone -> phone.getPhoneNumber())
                    .collect(Collectors.toList());
        }
        if (employee.getDepartment() == null) {
            this.deptTitle = null;
        }

        else {
            this.deptTitle = employee.getDepartment().getTitle();
        }

        if (employee.getExaminations() == null) {
            this.examinations = null;
        } else {
            this.examinations = employee.getExaminations().stream()
                    .map(examination -> examination.getId())
                    .collect(Collectors.toList());
        }
    }

}