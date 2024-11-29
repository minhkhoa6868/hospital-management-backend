import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.presistence.*;

@Entity
@Table(name="Medication")
public class Medication{
    
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MCode;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private Integer Price;

    @Column(nullable = false)
    private Integer Quantity;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "Expiration Date")
    private Date ExpirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "m_status", nullable = false, columnDefinition = "ENUM('AVAILABLE', 'OUT_OF_STOCK', 'EXPIRED')")
    private MedicaionStatus Status;

    public MedicationStatus getStatus() {
        return Status;
    }
}