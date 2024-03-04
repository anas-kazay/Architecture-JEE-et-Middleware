package ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories;

import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.Medecin;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Medecin findByNom(String nom);
}
