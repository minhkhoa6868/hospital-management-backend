package com.service;

import com.model.Employee;
import com.model.Examination;
import com.model.Employee_type;
import com.dto.ExaminationDTO;
import com.exception.NotFoundException;
import com.repository.ExaminationRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExaminationService {
    private final ExaminationRepository examinationRepository;
    private final EmployeeService employeeService;

    public ExaminationService(ExaminationRepository examinationRepository, EmployeeService employeeService) {
        this.examinationRepository = examinationRepository;
        this.employeeService = employeeService;
    }

    // handle find examination by id
    public Examination findExamination(long id) {
        Optional<Examination> targetExamination = this.examinationRepository.findById(id);

        if (targetExamination.isPresent()) {
            return targetExamination.get();
        }

        return null;
    }

    // handle get all examination
    public List<ExaminationDTO> handleGetAllExamination() {
        List<Examination> examinations = this.examinationRepository.findAll();

        return examinations.stream().map(ExaminationDTO::new).toList();
    }

    // handle get one examination
    public ExaminationDTO handleGetOneExamination(long id) {
        Examination targetExamination = this.findExamination(id);

        if (targetExamination == null) {
            throw new NotFoundException("Examination not found");
        }

        return new ExaminationDTO(targetExamination);
    }

    // handle create examination
    public ExaminationDTO handleCreateExamination(Examination examination) {
        Examination newExamination = new Examination();

        newExamination.setDate(examination.getDate());
        newExamination.setDiagnose(examination.getDiagnose());
        newExamination.setFee(examination.getFee());
        newExamination.setNextDate(examination.getNextDate());

        if (examination.getExamineDoctor() == null) {
            this.examinationRepository.disableForeignKeyChecks();
        }

        else {
            Employee doctor = this.employeeService.findEmployee(examination.getExamineDoctor().getEcode());
            Employee_type Doctor = Employee_type.Doctor;

            if (doctor == null) {
                throw new NotFoundException("Doctor not found");
            }

            if (doctor.getEmployeeType() != Doctor) {
                throw new NotFoundException("Employee is not a doctor");
            }

            newExamination.setExamineDoctor(doctor);
        }

        this.examinationRepository.enableForeignKeyChecks();

        Examination savedExamination = this.examinationRepository.save(newExamination);

        return new ExaminationDTO(savedExamination);
    }

    // handle get all examination of doctor
    public List<ExaminationDTO> handleGetAllExaminationOfDoctor(long doctorCode) {
        Employee doctor = this.employeeService.findEmployee(doctorCode);
        Employee_type Doctor = Employee_type.Doctor;

        if (doctor == null) {
            throw new NotFoundException("Doctor not found");
        }

        if (doctor.getEmployeeType() != Doctor) {
            throw new NotFoundException("Employee is not a doctor");
        }

        List<Examination> examinations = this.examinationRepository.findByDoctorCode(doctorCode);

        return examinations.stream().map(ExaminationDTO::new).toList();
    }
}
