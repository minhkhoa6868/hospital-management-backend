import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.presistence.*;

@Entity
@Table(name="Patients")
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

    @Column(nullable = false, name = "First Name")
    private String first_name;

    @Column(nullable = false, name = "Last Name")
    private String last_name;

    @Column(nullable = false)
    private Boolean Gender;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "Date of Birth")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date DoB;

    @Column(nullable = false)
    private String Address;

    @Column(nullable = false, name = "Patient Type")
    private PatientType patient_type;

    public PatientType getPatientType() {
        return patient_type;
    }
    
}