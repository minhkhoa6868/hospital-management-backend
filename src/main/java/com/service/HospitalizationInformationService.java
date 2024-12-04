package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dto.HospitalizationInformationDTO;
import com.model.Employee;
import com.model.Employee_type;
import com.model.HospitalizationInformation;
import com.model.Patients;
import com.model.Patients.PatientType;
import com.repository.HospitalizationInformationRepository;

import jakarta.transaction.Transactional;

import com.exception.NotFoundException;

@Service
@Transactional
public class HospitalizationInformationService {
    private final HospitalizationInformationRepository hospitalizationInformationRepository;
    private final EmployeeService employeeService;
    private final PatientService patientService;

    public HospitalizationInformationService(HospitalizationInformationRepository hospitalizationInformationRepository,
            EmployeeService employeeService,
            PatientService patientService) {
        this.hospitalizationInformationRepository = hospitalizationInformationRepository;
        this.employeeService = employeeService;
        this.patientService = patientService;
    }

    // find information
    public HospitalizationInformation findInformation(long id) {
        Optional<HospitalizationInformation> targetInformation = this.hospitalizationInformationRepository.findById(id);

        if (targetInformation.isPresent()) {
            return targetInformation.get();
        }

        return null;
    }

    // handle get all
    public List<HospitalizationInformationDTO> handleGetAllInformation() {
        List<HospitalizationInformation> allInformation = this.hospitalizationInformationRepository.findAll();

        return allInformation.stream().map(HospitalizationInformationDTO::new).toList();
    }

    public HospitalizationInformationDTO handleGetOneInformation(long id) {
        HospitalizationInformation targetInformation = this.findInformation(id);

        if (targetInformation == null) {
            throw new NotFoundException("Information not found");
        }

        return new HospitalizationInformationDTO(targetInformation);
    }

    // handle create information
    public HospitalizationInformationDTO handleCreateInformation(
            HospitalizationInformation hospitalizationInformation) {
        HospitalizationInformation newInformation = new HospitalizationInformation();

        newInformation.setDateOfAdmission(hospitalizationInformation.getDateOfAdmission());
        newInformation.setDateOfDischarge(hospitalizationInformation.getDateOfDischarge());
        newInformation.setDiagnosis(hospitalizationInformation.getDiagnosis());
        newInformation.setFee(hospitalizationInformation.getFee());
        newInformation.setSickroom(hospitalizationInformation.getSickroom());

        if (hospitalizationInformation.getTakeCareNurse() == null) {
            this.hospitalizationInformationRepository.disableForeignKeyChecks();
        }

        else {
            Employee nurse = this.employeeService
                    .findEmployee(hospitalizationInformation.getTakeCareNurse().getEcode());
            Employee_type Nurse = Employee_type.Nurse;

            if (nurse == null) {
                throw new NotFoundException("Nurse not found");
            }

            if (nurse.getEmployeeType() != Nurse) {
                throw new NotFoundException("Employee is not a nurse");
            }

            newInformation.setTakeCareNurse(nurse);
        }

        if (hospitalizationInformation.getTakeCarePatient() == null) {
            this.hospitalizationInformationRepository.disableForeignKeyChecks();
        }

        else {
            Patients patient = this.patientService.findPatient(hospitalizationInformation.getTakeCarePatient().getPcode());
            PatientType Inpatient = PatientType.Inpatient;

            if (patient == null) {
                throw new NotFoundException("Patient not found");
            }

            if (patient.getPatientType() != Inpatient) {
                throw new NotFoundException("Patient is not an inpatient");
            }

            newInformation.setTakeCarePatient(patient);
        }

        this.hospitalizationInformationRepository.enableForeignKeyChecks();

        HospitalizationInformation savedInformation = this.hospitalizationInformationRepository
                .save(newInformation);

        return new HospitalizationInformationDTO(savedInformation);
    }

    // handle get all hospitalization information of a nurse
    public List<HospitalizationInformationDTO> handleGetAllHospitalizationInformationOfNurse(long nurseCode) {
        Employee nurse = this.employeeService.findEmployee(nurseCode);

        Employee_type Nurse = Employee_type.Nurse;

        if (nurse == null) {
            throw new NotFoundException("Nurse not found");
        }

        if (nurse.getEmployeeType() != Nurse) {
            throw new NotFoundException("Employee is not a nurse");
        }

        List<HospitalizationInformation> allInformation = this.hospitalizationInformationRepository
                .findByNurseCode(nurseCode);

        return allInformation.stream().map(HospitalizationInformationDTO::new).toList();
    }
}
