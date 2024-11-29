import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.presistence.*;

@Entity
@Table(name="Patients", schema = "bkproject")
public class Patients{
    
    public enum PatientType{
        INPATIENT,
        OUTPATIENT;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String PCode;

    public String getPCode() {
        return PCode;
    }

    @Column(nullable = false, name = "First Name", columnDefinition = "VARCHAR(50)")
    private String first_name;

    @Column(nullable = false, name = "Last Name", columnDefinition = "VARCHAR(50)")
    private String last_name;

    @Column(nullable = false, columnDefinition = "ENUM('Male,Female')")
    private Gender gender;

    @Column(nullable = false, columnDefinition = "DATE", name = "Date of Birth")
    private LocalDate DoB;

    @Column(nullable = false, columnDefinition = "VARCHAR(200)")
    private String Address;

    @Enumerated(EnumType.STRING)
    @Column(name = "Patient Type", nullable = false, columnDefinition = "ENUM('Inpatient','Outpatient')")
    private PatientType patient_type;

    public PatientType getPatientType() {
        return patient_type;
    }

    
    
}