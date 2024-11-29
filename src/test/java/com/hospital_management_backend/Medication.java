import java.time.Date;

import jakarta.presistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Medication")
public class Medication{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MCode;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String Name;

    @Column(nullable = false)
    private Integer Price;

    @Column(nullable = false)
    private Integer Quantity;

    @Column(nullable = false, name = "Expiration Date", columnDefinition = "DATE")
    private LocalDate ExpirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "m_status", nullable = false, columnDefinition = "ENUM('AVAILABLE', 'OUT_OF_STOCK', 'EXPIRED')")
    private MedicaionStatus Status;

    public MedicationStatus getStatus() {
        return Status;
    }
}