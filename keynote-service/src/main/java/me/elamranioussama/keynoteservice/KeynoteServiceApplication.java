package me.elamranioussama.keynoteservice;

import me.elamranioussama.keynoteservice.entities.Keynote;
import me.elamranioussama.keynoteservice.repositories.KeynoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class KeynoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeynoteServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KeynoteRepository repository) {
        return args -> {
            repository.saveAll(
                    java.util.List.of(
                            Keynote.builder()
                                    .lastName("Smith")
                                    .firstName("Alice")
                                    .email("alice.smith@example.com")
                                    .function("Microservices Architecture")
                                    .build(),
                            Keynote.builder()
                                    .lastName("Johnson")
                                    .firstName("Bob")
                                    .email("bob.johnson@example.com")
                                    .function("Cloud Computing Trends")
                                    .build(),
                            Keynote.builder()
                                    .lastName("Lee")
                                    .firstName("Carol")
                                    .email("carol.lee@example.com")
                                    .function("AI and Machine Learning")
                                    .build()
                    )
            );
        };
    }
}
