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

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private Boolean gender;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;

    private String address;

    @Column(nullable = false)
    private PatientType patient_type;
}