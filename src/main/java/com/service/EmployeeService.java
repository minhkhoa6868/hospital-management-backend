package com.service;

import com.dto.EmployeeDTO;
import com.exception.NotFoundException;
import com.model.Department;
import com.model.Employee;
import com.model.Employee_type;
import com.repository.DepartmentRepository;
import com.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    // handle get all employees
    public List<EmployeeDTO> handleGetAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(EmployeeDTO::new).toList();
    }

    // find employee
    public Employee findEmployee(long code) {
        Optional<Employee> targetEmployee = this.employeeRepository.findByEcode(code);

        if (targetEmployee.isPresent()) {
            return targetEmployee.get();
        }

        return null;
    }

    // handle get one employee
    public EmployeeDTO handleGetOneEmployee(long code) {
        Employee employee = this.findEmployee(code);

        if (employee == null) {
            throw new NotFoundException("Employee not found");
        }

        return new EmployeeDTO(employee);
    }

    // handle create employee
    public Employee handleCreateEmployee(EmployeeDTO employeeDTO) {
        Employee newEmployee = new Employee();

        newEmployee.setFirstName(employeeDTO.getFirstName());
        newEmployee.setLastName(employeeDTO.getLastName());
        newEmployee.setDOB(employeeDTO.getDOB());
        newEmployee.setGender(employeeDTO.getGender());
        newEmployee.setStartDate(employeeDTO.getStartDate());
        newEmployee.setAddress(employeeDTO.getAddress());
        newEmployee.setSpeName(employeeDTO.getSpeName());
        newEmployee.setSpeDegreeYear(employeeDTO.getSpeDegreeYear());
        newEmployee.setEmployeeType(employeeDTO.getEmployeeType());
        newEmployee.setPhone_number(employeeDTO.getPhoneNumber());

        // handle reference to department
        if (employeeDTO.getDeptTitle() != null && !employeeDTO.getDeptTitle().isEmpty()) {
            Optional<Department> targetDepartment = this.departmentRepository.findByTitle(employeeDTO.getDeptTitle());

            if (targetDepartment.isPresent()) {
                newEmployee.setDepartment(targetDepartment.get());
            }

            else {
                throw new NotFoundException("Department not found");
            }
        }

        else {
            newEmployee.setDepartment(null);
        }

        return employeeRepository.save(newEmployee);
    }

    // handle update employee
    public EmployeeDTO handleUpdateEmployee(long code, EmployeeDTO employeeDTO) {
        Employee updatedEmployee = this.findEmployee(code);

        if (updatedEmployee == null) {
            throw new NotFoundException("Employee not found");
        }

        updatedEmployee.setFirstName(employeeDTO.getFirstName());
        updatedEmployee.setLastName(employeeDTO.getLastName());
        updatedEmployee.setDOB(employeeDTO.getDOB());
        updatedEmployee.setGender(employeeDTO.getGender());
        updatedEmployee.setAddress(employeeDTO.getAddress());
        updatedEmployee.setSpeName(employeeDTO.getSpeName());
        updatedEmployee.setSpeDegreeYear(employeeDTO.getSpeDegreeYear());
        updatedEmployee.setEmployeeType(employeeDTO.getEmployeeType());
        updatedEmployee.setPhone_number(employeeDTO.getPhoneNumber());

        // handle reference to department
        if (employeeDTO.getDeptTitle() != null && !employeeDTO.getDeptTitle().isEmpty()) {
            Optional<Department> targetDepartment = this.departmentRepository.findByTitle(employeeDTO.getDeptTitle());

            if (targetDepartment.isPresent()) {
                updatedEmployee.setDepartment(targetDepartment.get());
            }

            else {
                throw new NotFoundException("Department not found");
            }
        }

        else {
            updatedEmployee.setDepartment(null);
        }

        Employee savedEmployee = employeeRepository.save(updatedEmployee);

        return new EmployeeDTO(savedEmployee);
    }

    // handle delete all employees
    public void handleDeleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    // handle delete employee
    public void handleDeleteEmployee(long code) {
        Employee employee = this.findEmployee(code);

        if (employee == null) {
            throw new NotFoundException("Employee not found");
        }

        employeeRepository.delete(employee);
    }

    // handle get all doctor
    public List<EmployeeDTO> handleGetAllDoctors() {
        List<Employee> doctors = employeeRepository.findByEmployeeType(Employee_type.Doctor);

        return doctors.stream().map(EmployeeDTO::new).toList();
    }
}
