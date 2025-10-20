package me.elamranioussama.conferenceservice.web;

import me.elamranioussama.conferenceservice.entities.Conference;
import me.elamranioussama.conferenceservice.entities.Review;
import me.elamranioussama.conferenceservice.feign.KeynoteRestClient;
import me.elamranioussama.conferenceservice.repositories.ConferenceRepository;
import me.elamranioussama.conferenceservice.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceRestController {
    private KeynoteRestClient keynoteRestClient;

    private final ConferenceRepository conferenceRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ConferenceRestController(ConferenceRepository conferenceRepository,
                                    ReviewRepository reviewRepository) {
        this.conferenceRepository = conferenceRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public List<Conference> listConferences() {
        return conferenceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conference> getConference(@PathVariable Long id) {
        return conferenceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Conference> createConference(@RequestBody Conference conference) {
        Conference saved = conferenceRepository.save(conference);
        return ResponseEntity.created(URI.create("/api/conferences/" + saved.getId())).body(saved);
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<?> addReview(@PathVariable Long id, @RequestBody Review review) {
        Optional<Conference> opt = conferenceRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Conference conference = opt.get();
        review.setConference(conference);
        Review saved = reviewRepository.save(review);

        if (!conference.getReviews().contains(saved)) {
            conference.getReviews().add(saved);
            conferenceRepository.save(conference);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/keynotes/availability")
    public ResponseEntity<String> keynoteAvailability() {
        if (keynoteRestClient == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("KeynoteRestClient not configured");
        }
        return ResponseEntity.ok("KeynoteRestClient available");
    }
}
