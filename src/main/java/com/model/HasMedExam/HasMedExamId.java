package com.model.HasMedExam;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class HasMedExamId implements Serializable {
    private Long examId;
    private Long medCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasMedExamId that = (HasMedExamId) o;
        return Objects.equals(examId, that.examId) &&
               Objects.equals(medCode, that.medCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId, medCode);
    }

}
