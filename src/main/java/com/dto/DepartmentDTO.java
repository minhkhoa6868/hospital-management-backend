package com.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.model.Department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {
    private String title;
    private Long dean_code;
    private List<EmployeeDTO> employees;

    public DepartmentDTO(Department department) {
        this.title = department.getTitle();

        if (department.getDean() == null) {
            this.dean_code = null;
        }
        else {
            this.dean_code = department.getDean().getEcode();
        }

        if (department.getEmployees() == null) {
            this.employees = null;
        }

        else {
            this.employees = department.getEmployees().stream()
                            .map(EmployeeDTO::new)
                            .collect(Collectors.toList());
        }
    }
}
