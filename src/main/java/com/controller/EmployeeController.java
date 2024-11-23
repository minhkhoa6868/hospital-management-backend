package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.EmployeeDTO;
import com.model.Employee;
import com.service.EmployeeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // return all employees
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.handleGetAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // return information of one employee
    @GetMapping("{code}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable long code) {
        EmployeeDTO employeeDTO = employeeService.handleGetOneEmployee(code);
        return ResponseEntity.ok(employeeDTO);
    }

    // handle create employee
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.handleCreateEmployee(employee);

        EmployeeDTO employeeDTO = new EmployeeDTO(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDTO);
    }

    // handle update employee
    @PutMapping("/{code}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable long code, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployeeDTO = employeeService.handleUpdateEmployee(code, employeeDTO);

        return ResponseEntity.ok(updatedEmployeeDTO);
    }

    // handle delete all employees
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllEmployees() {
        employeeService.handleDeleteAllEmployees();

        return ResponseEntity.ok("All employees deleted successfully");
    }

    // handle delete one employee
    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long code) {
        employeeService.handleDeleteEmployee(code);

        return ResponseEntity.ok("Employee deleted successfully");
    }

    // handle get all employee phone numbers
    @GetMapping("/{code}/phone")
    public ResponseEntity<List<String>> getAllEmployeePhoneNumbers(@PathVariable long code) {
        List<String> phoneNumbers = employeeService.handleGetAllEmployeePhones(code);

        return ResponseEntity.ok(phoneNumbers);
    }
}
