import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.presistence.*;

@Entity
@Table(name="Medication")
public class Medication{

    public enum MedStatus {
        AVAILABLE,
        OUT_OF_STOCK,
        EXPIRED,
        RESERVED,
        DISPENSED,
        PENDING_RESTOCK
    }
    
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String MCode;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private double Price;

    @Column(nullable = false)
    private Long Quantity;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "Expiration Date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date Expiration_Date;

    @Column(nullable = false)
    private MedStatus Status;

    public MedStatus getStatus() {
        return Status;
    }
}