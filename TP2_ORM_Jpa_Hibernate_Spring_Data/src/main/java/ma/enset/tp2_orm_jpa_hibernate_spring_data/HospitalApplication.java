package ma.enset.tp2_orm_jpa_hibernate_spring_data;

import ma.enset.tp2_orm_jpa_hibernate_spring_data.entities.*;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories.ConsultationRepository;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories.MedecinRepository;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories.PatientRepository;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.repositories.RendezVousRepository;
import ma.enset.tp2_orm_jpa_hibernate_spring_data.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication{


    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }


    @Bean
    CommandLineRunner start(IHospitalService iHospitalService){
        return args->{
            Stream.of("Oussama","Anas","Yassin").forEach(
                    name->{
                        Medecin medecin = new Medecin();
                        medecin.setNom(name);
                        medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                        medecin.setEmail(name+"-medecin@gmail.com");
                        iHospitalService.saveMedecin(medecin);
                    }
            );
            Stream.of("Mohamed","Hassan","Najat").forEach(
                    name->{
                        Patient patient = new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(false);
                        iHospitalService.savePatient(patient);
                    }
            );
            Patient patient = iHospitalService.findPatientByName("Hassan");
            Medecin medecin = iHospitalService.findMedecinByName("Oussama");
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient);
            iHospitalService.saveRendezVous(rendezVous);


            RendezVous rendezVous1 = iHospitalService.findRendezVousById(1l);

            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de consultation");
            iHospitalService.saveConsultation(consultation);


        };
    }
}
