package com.service;

import com.dto.EmployeeDTO;
import com.model.Department;
import com.model.Employee;
import com.model.Employee_phone;
import com.repository.DepartmentRepository;
import com.repository.EmployeeRepository;

import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    // handle get all employees
    @Transactional
    public List<EmployeeDTO> handleGetAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

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
    public Employee handleCreateEmployee(EmployeeDTO employeeDTO) {
        System.out.println(employeeDTO);

        Employee newEmployee = new Employee();

        this.employeeRepository.disableForeignKeyChecks();

        newEmployee.setFirstName(employeeDTO.getFirstName());
        newEmployee.setLastName(employeeDTO.getLastName());
        newEmployee.setDOB(employeeDTO.getDOB());
        newEmployee.setGender(employeeDTO.getGender());
        newEmployee.setStartDate(employeeDTO.getStartDate());
        newEmployee.setAddress(employeeDTO.getAddress());
        newEmployee.setSpeName(employeeDTO.getSpeName());
        newEmployee.setSpeDegreeYear(employeeDTO.getSpeDegreeYear());
        newEmployee.setEmployeeType(employeeDTO.getEmployeeType());

        // handle create phone number
        if (employeeDTO.getPhoneNumbers() != null && !employeeDTO.getPhoneNumbers().isEmpty()) {
            for (String phoneNumber : employeeDTO.getPhoneNumbers()) {
                // Avoid duplicates or null values
                if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                    Employee_phone phone = new Employee_phone();
                    phone.setPhoneNumber(phoneNumber.trim());
                    phone.setEmployee(newEmployee); // Establish relationship
                    newEmployee.getPhone_numbers().add(phone); // Add to employee
                }
            }
        }

        // handle reference to department
        if (employeeDTO.getDeptTitle() != null && !employeeDTO.getDeptTitle().isEmpty()) {
            Optional<Department> targetDepartment = this.departmentRepository.findByTitle(employeeDTO.getDeptTitle());

            if (targetDepartment.isPresent()) {
                newEmployee.setDepartment(targetDepartment.get());
            }

            else {
                throw new RuntimeException("Department not found");
            }
        }

        this.employeeRepository.enableForeignKeyChecks();

        return employeeRepository.save(newEmployee);
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

        // Handle phone numbers
        // Step 1: Remove existing phone numbers explicitly
        Set<Employee_phone> existingPhones = updatedEmployee.getPhone_numbers();
        if (existingPhones != null) {
            existingPhones.forEach(phone -> phone.setEmployee(null)); // Break relationship
            existingPhones.clear();
        }

        // Step 2: Add new phone numbers from DTO
        if (employeeDTO.getPhoneNumbers() != null && !employeeDTO.getPhoneNumbers().isEmpty()) {
            for (String phoneNumber : employeeDTO.getPhoneNumbers()) {
                // Avoid duplicates or null values
                if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                    Employee_phone phone = new Employee_phone();
                    phone.setPhoneNumber(phoneNumber.trim());
                    phone.setEmployee(updatedEmployee); // Establish relationship
                    updatedEmployee.getPhone_numbers().add(phone); // Add to employee
                }
            }
        }

        // handle reference to department
        if (employeeDTO.getDeptTitle() != null && !employeeDTO.getDeptTitle().isEmpty()) {
            Optional<Department> targetDepartment = this.departmentRepository.findByTitle(employeeDTO.getDeptTitle());

            if (targetDepartment.isPresent()) {
                updatedEmployee.setDepartment(targetDepartment.get());
            }

            else {
                throw new RuntimeException("Department not found");
            }
        }

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
