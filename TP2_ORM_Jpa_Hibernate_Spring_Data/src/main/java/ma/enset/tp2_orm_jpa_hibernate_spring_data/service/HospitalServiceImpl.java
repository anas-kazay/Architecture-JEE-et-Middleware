package ma.enset.tp2_orm_jpa_hibernate_spring_data.service;

import jakarta.transaction.Transactional;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.Consultation;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.Medecin;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.Patient;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.RendezVous;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories.ConsultationRepository;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories.MedecinRepository;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories.PatientRepository;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private ConsultationRepository consultationRepository;
    private RendezVousRepository rendezVousRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, ConsultationRepository consultationRepository, RendezVousRepository rendezVousRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.consultationRepository = consultationRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Patient getPatient(Long id) {
        return patientRepository.getById(id);
    }

    @Override
    public Patient updatePatient(Patient patient, Patient updatedPatient) {
        patient.setNom(updatedPatient.getNom());
        patient.setDateNaissance(updatedPatient.getDateNaissance());
        patient.setMalade(updatedPatient.getMalade());
        return patientRepository.save(patient);
    }


    public void deletePatient(Long id) {

            Optional<Patient> patient1 = patientRepository.findById(id);
            if (patient1.isPresent()) {
                // Step 2: If the patient exists, delete it
                patientRepository.delete(patient1.get());
            } else {
                // Handle case where patient with given ID is not found
                throw new RuntimeException("Patient not found with id: " + id);
            }


    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findPatientByName(String name) {
        return patientRepository.findByNom(name);
    }

    @Override
    public Medecin findMedecinByName(String name) {
        return medecinRepository.findByNom(name);
    }

    @Override
    public void saveRendezVous(RendezVous rendezVous) {
        rendezVousRepository.save(rendezVous);
    }

    @Override
    public RendezVous findRendezVousById(Long id) {
        return rendezVousRepository.findById(1l).orElse(null);
    }
}
