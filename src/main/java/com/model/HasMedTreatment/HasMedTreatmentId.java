package com.model.HasMedTreatment;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class HasMedTreatmentId implements Serializable {
    private Long medCode;
    private Long treatId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasMedTreatmentId that = (HasMedTreatmentId) o;
        return Objects.equals(treatId, that.treatId) &&
               Objects.equals(medCode, that.medCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treatId, medCode);
    }
}
