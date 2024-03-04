package ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories;

import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.Patient;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
}
