package me.elamranioussama.conferenceservice.feign;

import me.elamranioussama.conferenceservice.models.Keynote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "keynote-service")
public interface KeynoteRestClient {
    @GetMapping("/api/keynotes/{id}")
    Keynote getKeynote(@PathVariable Long id);

    @GetMapping("/api/keynotes")
    PagedModel<Keynote> getAllKeynotes();
}
