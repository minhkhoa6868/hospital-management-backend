package com.service;

import com.model.Examination;
import com.dto.ExaminationDTO;
import com.exception.NotFoundException;
import com.repository.ExaminationRepository;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExaminationService {
    private final ExaminationRepository examinationRepository;

    public ExaminationService(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
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
        Examination savedExamination = this.examinationRepository.save(examination);

        return new ExaminationDTO(savedExamination);
    }
}
