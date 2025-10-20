package me.elamranioussama.conferenceservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Keynote {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String function;
}
