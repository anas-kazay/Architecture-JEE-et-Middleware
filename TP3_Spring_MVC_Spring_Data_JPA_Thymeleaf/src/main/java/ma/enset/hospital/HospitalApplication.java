package ma.enset.hospital;

import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"mohammed",new Date(),false,466));
        patientRepository.save(new Patient(null,"Dounia",new Date(),false,177));
        patientRepository.save(new Patient(null,"Doha",new Date(),true,566));
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunnerJdbcUsers(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user1").
                            password(passwordEncoder.encode("1234")).
                            roles("USER").
                            build()
            );
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user2").
                            password(passwordEncoder.encode("1234")).
                            roles("USER").
                            build()
            );
            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin").
                            password(passwordEncoder.encode("1234")).
                            roles("USER","ADMIN").
                            build()
            );

        };
    }
}
