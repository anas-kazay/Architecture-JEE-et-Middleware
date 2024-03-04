package ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories;

import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByNom(String nom);
}
