package ma.enset.tp2_orm_jpa_hibernate_spring_data.service;

import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.Consultation;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.Medecin;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.Patient;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.RendezVous;

import java.util.List;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
    Patient getPatient(Long id);
    Patient updatePatient(Patient patient,Patient updatedPatient);
    void deletePatient(Long id);
    List<Patient> getAllPatients();

    Patient findPatientByName(String name);
    Medecin findMedecinByName(String name);
    void saveRendezVous(RendezVous rendezVous);
    RendezVous findRendezVousById(Long id);


}
