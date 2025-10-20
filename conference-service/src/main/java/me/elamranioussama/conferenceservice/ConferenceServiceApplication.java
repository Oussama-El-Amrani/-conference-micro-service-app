package me.elamranioussama.conferenceservice;

import me.elamranioussama.conferenceservice.entities.Conference;
import me.elamranioussama.conferenceservice.entities.Review;
import me.elamranioussama.conferenceservice.entities.enums.ConferenceType;
import me.elamranioussama.conferenceservice.repositories.ConferenceRepository;
import me.elamranioussama.conferenceservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ConferenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(ConferenceRepository conferenceRepository,
                                             ReviewRepository reviewRepository) {
        return args -> {

            // Conference 1
            Conference c1 = new Conference();
            c1.setTitle("Microservices Architecture");
            c1.setType(ConferenceType.ACADEMIC);
            c1.setDate(LocalDate.parse("2024-04-10"));
            c1.setDurationInMinutes(90);
            c1.setNumberOfRegistrants(120);

            Review c1r1 = new Review();
            c1r1.setDate(LocalDate.parse("2024-04-11"));
            c1r1.setText("Great technical depth and practical examples.");
            c1r1.setStars(5);

            Review c1r2 = new Review();
            c1r2.setDate(LocalDate.parse("2024-04-11"));
            c1r2.setText("Well presented but a bit fast.");
            c1r2.setStars(4);

            // Conference 2
            Conference c2 = new Conference();
            c2.setTitle("Cloud Computing Trends");
            c2.setType(ConferenceType.COMMERCIAL);
            c2.setDate(LocalDate.parse("2024-05-20"));
            c2.setDurationInMinutes(60);
            c2.setNumberOfRegistrants(200);

            Review c2r1 = new Review();
            c2r1.setDate(LocalDate.parse("2024-05-21"));
            c2r1.setText("Useful market insights.");
            c2r1.setStars(4);

            // Conference 3
            Conference c3 = new Conference();
            c3.setTitle("AI and Machine Learning");
            c3.setType(ConferenceType.ACADEMIC);
            c3.setDate(LocalDate.parse("2024-06-15"));
            c3.setDurationInMinutes(120);
            c3.setNumberOfRegistrants(180);

            Review c3r1 = new Review();
            c3r1.setDate(LocalDate.parse("2024-06-16"));
            c3r1.setText("Excellent coverage of practical ML.");
            c3r1.setStars(5);

            Review c3r2 = new Review();
            c3r2.setDate(LocalDate.parse("2024-06-16"));
            c3r2.setText("Too many advanced topics for beginners.");
            c3r2.setStars(3);

            // Persist conferences (reviews cascade if configured)
            conferenceRepository.saveAll(List.of(c1, c2, c3));

            // compute and persist average score for each conference
            List<Conference> saved = conferenceRepository.findAll();
            saved.forEach(conf -> {
                double avg = conf.getReviews().stream()
                        .mapToInt(Review::getStars)
                        .average()
                        .orElse(0.0);
                conf.setScore(avg);
            });
            conferenceRepository.saveAll(saved);
        };
    }
}
