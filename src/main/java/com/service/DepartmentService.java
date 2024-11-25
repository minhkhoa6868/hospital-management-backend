package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.DepartmentDTO;
import com.dto.EmployeeDTO;
import com.model.Department;
import com.model.Employee;
import com.repository.DepartmentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
    }

    // handle find deparment
    public Department findDepartment(long dcode) {
        Optional<Department> department = this.departmentRepository.findByDcode(dcode);

        if (department.isPresent()) {
            return department.get();
        } else {
            return null;
        }
    }

    // handle get all departments
    public List<DepartmentDTO> handleGetAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map(department -> new DepartmentDTO(department)).collect(Collectors.toList());
    }

    // handle get one deparment
    public DepartmentDTO handleGetOneDepartment(long dcode) {
        Department getDepartment = findDepartment(dcode);

        if (getDepartment == null) {
            throw new RuntimeException("Department not found");
        }

        return new DepartmentDTO(getDepartment);
    }

    // handle create department
    public DepartmentDTO handleCreateDepartment(Department department) {
        if (this.departmentRepository.findByTitle(department.getTitle()).isPresent()) {
            throw new RuntimeException("Department already exists");
        }

        Department newDepartment = new Department();

        newDepartment.setTitle(department.getTitle());

        if (department.getDean() == null) {
            this.departmentRepository.disableForeignKeyChecks();
        }

        else {
            Employee dean = this.employeeService.findEmployee(department.getDean().getEcode());

            if (dean == null) {
                throw new RuntimeException("Dean not found");
            }

            newDepartment.setDean(dean);
        }

        this.departmentRepository.enableForeignKeyChecks();

        Department savedDepartment = departmentRepository.save(newDepartment);

        return new DepartmentDTO(savedDepartment);
    }

    // handle update department
    public DepartmentDTO handleUpdateDepartment(long dcode, Department department) {
        // check if this id existses
        Department updatedDepartment = findDepartment(dcode);

        if (updatedDepartment == null) {
            throw new RuntimeException("Department not found");
        }

        Employee dean = this.employeeService.findEmployee(department.getDean().getEcode());

        if (this.departmentRepository.findByTitle(department.getTitle()).isPresent()) {
            throw new RuntimeException("Department already exists");
        }

        if (department.getDean() == null) {
            updatedDepartment.setDean(null);
        }

        if (dean == null) {
            throw new RuntimeException("Dean not found");
        }

        updatedDepartment.setTitle(department.getTitle());
        updatedDepartment.setDean((department.getDean()));

        Department savedDepartment = departmentRepository.save(updatedDepartment);

        return new DepartmentDTO(savedDepartment);
    }

    // handle delete all department
    public void handleDeleteAllDepartment() {
        departmentRepository.deleteAll();
    }

    // handle delete one department
    public void handleDeleteOneDepartment(long dcode) {
        Department department = findDepartment(dcode);

        if (department == null) {
            throw new RuntimeException("Department not found");
        }

        departmentRepository.delete(department);
    }

    // handle get dean
    public EmployeeDTO handleGetDean(long dcode) {
        Department department = findDepartment(dcode);

        if (department == null) {
            throw new RuntimeException("Department not found");
        }

        if (department.getDean() == null) {
            throw new RuntimeException("Dean not found");
        }

        return new EmployeeDTO(department.getDean());
    }

    // handle get all employees in department
    public List<EmployeeDTO> handleGetAllEmployees(long dcode) {
        Department department = findDepartment(dcode);

        if (department == null) {
            throw new RuntimeException("Department not found");
        }

        return department.getEmployees().stream().map(EmployeeDTO::new).toList();
    }
}