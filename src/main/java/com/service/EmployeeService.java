package com.service;

import com.dto.EmployeeDTO;
import com.model.Employee;
import com.model.Employee_phone;
import com.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // handle get all employees
    @Transactional
    public List<EmployeeDTO> handleGetAllEmployees() {
        List<Employee> employees = employeeRepository.findAllEmployees();

        return employees.stream().map(EmployeeDTO::new).toList();
    }

    // find employee
    @Transactional
    public Employee findEmployee(long code) {
        Optional<Employee> targetEmployee = this.employeeRepository.findByEcode(code);

        if (targetEmployee.isPresent()) {
            return targetEmployee.get();
        }

        return null;
    }

    // handle get one employee
    @Transactional
    public EmployeeDTO handleGetOneEmployee(long code) {
        Employee employee = this.findEmployee(code);

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        return new EmployeeDTO(employee);
    }

    // handle create employee
    @Transactional
    public Employee handleCreateEmployee(Employee employee) {
        Employee newEmployee = this.findEmployee(employee.getEcode());

        if (newEmployee != null) {
            throw new RuntimeException("Employee already exists");
        }

        // Associate phone numbers with the employee
        if (employee.getPhone_numbers() != null) {
            for (Employee_phone phone : employee.getPhone_numbers()) {
                phone.setEmployee(employee); // Set the relationship
            }
        }

        return employeeRepository.save(employee);
    }

    // handle update employee
    @Transactional
    public EmployeeDTO handleUpdateEmployee(long code, EmployeeDTO employeeDTO) {
        Employee updatedEmployee = this.findEmployee(code);

        if (updatedEmployee == null) {
            throw new RuntimeException("Employee not found");
        }

        updatedEmployee.setFirstName(employeeDTO.getFirstName());
        updatedEmployee.setLastName(employeeDTO.getLastName());
        updatedEmployee.setDOB(employeeDTO.getDOB());
        updatedEmployee.setGender(employeeDTO.getGender());
        updatedEmployee.setAddress(employeeDTO.getAddress());
        updatedEmployee.setSpeName(employeeDTO.getSpeName());
        updatedEmployee.setSpeDegreeYear(employeeDTO.getSpeDegreeYear());
        updatedEmployee.setEmployeeType(employeeDTO.getEmployeeType());

        Employee savedEmployee = employeeRepository.save(updatedEmployee);

        return new EmployeeDTO(savedEmployee);
    }

    // handle delete all employees
    @Transactional
    public void handleDeleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    // handle delete employee
    @Transactional
    public void handleDeleteEmployee(long code) {
        Employee employee = this.findEmployee(code);

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        employeeRepository.delete(employee);
    }

    // handle get all phone numbers of employee
    @Transactional
    public List<String> handleGetAllEmployeePhones(long code) {
        Employee employee = this.findEmployee(code);

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        return employee.getPhone_numbers().stream()
                .map(Employee_phone::getPhoneNumber)
                .collect(Collectors.toList());
    }
}
