package com.dto;

import com.model.Department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {
    private Long dcode;
    private String title;
    private String deanName;
    private Integer numberOfEmployee;

    public DepartmentDTO() {}

    public DepartmentDTO(Department department) {
        this.dcode = department.getDcode();
        this.title = department.getTitle();

        if (department.getDean() == null) {
            this.deanName = null;
        }
        else {
            this.deanName = department.getDean().getLastName() + " " + department.getDean().getFirstName();
        }

        this.numberOfEmployee = department.getEmployees().size();
    }
}
