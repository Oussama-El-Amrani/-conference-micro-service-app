package me.elamranioussama.conferenceservice.repositories;


import me.elamranioussama.conferenceservice.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
