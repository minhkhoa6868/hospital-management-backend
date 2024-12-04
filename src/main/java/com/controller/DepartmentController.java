package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Department;
import com.service.DepartmentService;
import com.dto.DepartmentDTO;
import com.dto.EmployeeDTO;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:5173")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody Department dept) {
        DepartmentDTO newDepartment = this.departmentService.handleCreateDepartment(dept);

        return ResponseEntity.status(HttpStatus.CREATED).body(newDepartment);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment() {
        List<DepartmentDTO> departments = this.departmentService.handleGetAllDepartments();

        return ResponseEntity.ok(departments);
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDTO> getOneDepartment(@PathVariable long code) {
        DepartmentDTO department = this.departmentService.handleGetOneDepartment(code);

        return ResponseEntity.ok(department);
    }
    
    @PutMapping("/{code}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable long code, @RequestBody Department dept) {
        DepartmentDTO updatedDepartment = this.departmentService.handleUpdateDepartment(code, dept);

        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllDepartments() {
        this.departmentService.handleDeleteAllDepartment();

        return ResponseEntity.ok("All departments deleted successfully");
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteDepartment(@PathVariable long code) {
        this.departmentService.handleDeleteOneDepartment(code);

        return ResponseEntity.ok("Department deleted successfully");
    }

    // get dean of department
    @GetMapping("/{code}/dean")
    public ResponseEntity<EmployeeDTO> getDean(@PathVariable long code) {
        EmployeeDTO employee = this.departmentService.handleGetDean(code);

        return ResponseEntity.ok(employee);
    }

    // get all employees in department
    @GetMapping("/{code}/employee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@PathVariable long code) {
        List<EmployeeDTO> employees = this.departmentService.handleGetAllEmployees(code);

        return ResponseEntity.ok(employees);
    }
    
}
