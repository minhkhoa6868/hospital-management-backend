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
    private Long speDegreeYear;
    private Employee_type employeeType;
    private String phoneNumber;
    private String deptTitle;
    private List<ExaminationDTO> examinations;
    private List<HospitalizationInformationDTO> hospitalizationInformations;
    private List<TreatmentDTO> treatments;

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
        this.phoneNumber = employee.getPhone_number();

        if (employee.getDepartment() == null) {
            this.deptTitle = null;
        }

        else {
            this.deptTitle = employee.getDepartment().getTitle();
        }

        if (employee.getExaminations() == null) {
            this.examinations = null;
        }

        else {
            this.examinations = employee.getExaminations().stream()
                    .map(ExaminationDTO::new)
                    .collect(Collectors.toList());
        }

        if (employee.getHospitalizationInformations() == null) {
            this.hospitalizationInformations = null;
        }

        else {
            this.hospitalizationInformations = employee.getHospitalizationInformations().stream()
                    .map(HospitalizationInformationDTO::new)
                    .collect(Collectors.toList());
        }

        if (employee.getTreatments() == null) {
            this.treatments = null;
        }

        else {
            this.treatments = employee.getTreatments().stream()
                    .map(TreatmentDTO::new)
                    .collect(Collectors.toList());
        }
    }

}