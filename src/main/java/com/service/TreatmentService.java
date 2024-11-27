package com.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Employee;
import com.model.Employee_type;
import com.model.HospitalizationInformation;
import com.model.Treatment;
import com.repository.TreatmentRepository;

import jakarta.transaction.Transactional;

import com.dto.TreatmentDTO;
import com.exception.NotFoundException;

@Service
@Transactional
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final EmployeeService employeeService;
    private final HospitalizationInformationService hospitalizationInformationService;

    public TreatmentService(TreatmentRepository treatmentRepository, 
                            EmployeeService employeeService, 
                            HospitalizationInformationService hospitalizationInformationService) {
        this.treatmentRepository = treatmentRepository;
        this.employeeService = employeeService;
        this.hospitalizationInformationService = hospitalizationInformationService;
    }

    // handle find treatment
    public Treatment findTreatment(Long id) {
        Optional<Treatment> treatment = treatmentRepository.findById(id);

        if (treatment.isPresent()) {
            return treatment.get();
        } 

        return null;
    }

    // handle get all treatments
    public List<TreatmentDTO> handleGetAllTreatment() {
        List<Treatment> treatments = treatmentRepository.findAll();

        return treatments.stream().map(TreatmentDTO::new).toList();
    }

    // handle get one treatment
    public TreatmentDTO handleGetOneTreatment(long id) {
        Treatment treatment = this.findTreatment(id);

        if (treatment == null) {
            throw new NotFoundException("Treatment not found");
        }

        return new TreatmentDTO(treatment);
    }

    // handle create treatment
    public TreatmentDTO handleCreateTreatment(Treatment treatment) {
        Treatment newTreatment = new Treatment();

        newTreatment.setResult(treatment.getResult());
        newTreatment.setStartDate(treatment.getStartDate());
        newTreatment.setEndDate(treatment.getEndDate());

        if (treatment.getTreatDoctor() == null) {
            this.treatmentRepository.disableForeignKeyChecks();
        } 

        else {
            Employee doctor = this.employeeService.findEmployee(treatment.getTreatDoctor().getEcode());
            Employee_type Doctor = Employee_type.Doctor;

            if (doctor == null) {
                throw new NotFoundException("Doctor not found");
            }

            if (doctor.getEmployeeType() != Doctor) {
                throw new NotFoundException("Employee is not a doctor");
            }

            newTreatment.setTreatDoctor(doctor);
        }

        if (treatment.getTreatInformation() == null) {
            this.treatmentRepository.disableForeignKeyChecks();
        }

        else {
            HospitalizationInformation hospitalizationInformation = this.hospitalizationInformationService.findInformation(treatment.getTreatInformation().getId());

            if (hospitalizationInformation == null) {
                throw new NotFoundException("Hospitalization information not found");
            }

            newTreatment.setTreatInformation(hospitalizationInformation);
        }

        this.treatmentRepository.enableForeignKeyChecks();

        Treatment savedTreatment = treatmentRepository.save(newTreatment);

        return new TreatmentDTO(savedTreatment);
    }

    // handle get all treatment of doctor
    public List<TreatmentDTO> handleGetAllTreatmentOfDoctor(long doctorCode) {
        Employee doctor = this.employeeService.findEmployee(doctorCode);
        Employee_type Doctor = Employee_type.Doctor;

        if (doctor == null) {
            throw new NotFoundException("Doctor not found");
        }

        if (doctor.getEmployeeType() != Doctor) {
            throw new NotFoundException("Employee is not a doctor");
        }

        List<Treatment> treatments = treatmentRepository.findByDoctorCode(doctorCode);

        return treatments.stream().map(TreatmentDTO::new).toList();
    }

    // handle get all treatment of hospitalization information

    public List<TreatmentDTO> handleGetAllTreatmentOfHospitalization(long hospitalizationId) {
        HospitalizationInformation  hospitalizationInformation = this.hospitalizationInformationService.findInformation(hospitalizationId);

        if (hospitalizationInformation == null) {
            throw new NotFoundException("Hospitalization information not found");
        }

        List<Treatment> treatments = treatmentRepository.findByInformationId(hospitalizationId);

        return treatments.stream().map(TreatmentDTO::new).toList();
    }
}
