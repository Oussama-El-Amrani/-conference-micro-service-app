package me.elamranioussama.conferenceservice.repositories;

import me.elamranioussama.conferenceservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface ReviewRepository extends JpaRepository<Review,Long> {
}
