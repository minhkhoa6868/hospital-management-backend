package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.EmployeeDTO;
import com.dto.ExaminationDTO;
import com.dto.HospitalizationInformationDTO;
import com.dto.PatientDTO;
import com.dto.TreatmentDTO;
import com.model.Employee;
import com.service.EmployeeService;
import com.service.ExaminationService;
import com.service.HospitalizationInformationService;
import com.service.TreatmentService;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final HospitalizationInformationService hospitalizationInformationService;
    private final ExaminationService examinationService;
    private final TreatmentService treatmentService;

    public EmployeeController(EmployeeService employeeService, 
                                HospitalizationInformationService hospitalizationInformationService,
                                ExaminationService examinationService,
                                TreatmentService treatmentService) {
        this.employeeService = employeeService;
        this.hospitalizationInformationService = hospitalizationInformationService;
        this.examinationService = examinationService;
        this.treatmentService = treatmentService;
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
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        System.out.println(employeeDTO.getFirstName());
        Employee newEmployee = employeeService.handleCreateEmployee(employeeDTO);

        EmployeeDTO newEmployeeDTO = new EmployeeDTO(newEmployee);

        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployeeDTO);
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

    // get all hospitalization information of nurse
    @GetMapping("/{code}/hospitalization_information")
    public ResponseEntity<List<HospitalizationInformationDTO>> getAllHospitalizationInformationOfNurse(@PathVariable long code) {
        List<HospitalizationInformationDTO> hospitalizationInformation = hospitalizationInformationService.handleGetAllHospitalizationInformationOfNurse(code);

        return ResponseEntity.ok(hospitalizationInformation);
    }
    
    // get all examination of doctor
    @GetMapping("/{code}/examination")
    public ResponseEntity<List<ExaminationDTO>> getAllExaminationOfDoctor(@PathVariable long code) {
        List<ExaminationDTO> examinations = this.examinationService.handleGetAllExaminationOfDoctor(code);

        return ResponseEntity.ok(examinations);
    }

    // get all treatment of doctor
    @GetMapping("/{code}/treatment")
    public ResponseEntity<List<TreatmentDTO>> getAllTreatmentOfDoctor(@PathVariable long code) {
        List<TreatmentDTO> treatments = this.treatmentService.handleGetAllTreatmentOfDoctor(code);

        return ResponseEntity.ok(treatments);
    }

    // get all patient which are treated by doctor
    @GetMapping("/{code}/treatment/patient")
    public ResponseEntity<List<PatientDTO>> getAllPatientTreatedByDoctor(@PathVariable long code) {
        List<PatientDTO> patients = this.treatmentService.handleGetAllPatientTreatedByDoctor(code);

        return ResponseEntity.ok(patients);
    }
    
    // get all doctors
    @GetMapping("/all/doctor")
    public ResponseEntity<List<EmployeeDTO>> getAllDoctors() {
        List<EmployeeDTO> doctors = this.employeeService.handleGetAllDoctors();

        return ResponseEntity.ok(doctors);
    }
}
