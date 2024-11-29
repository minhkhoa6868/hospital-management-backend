packace com.repository;

import com.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,long>{
    
}