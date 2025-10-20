package me.elamranioussama.conferenceservice.entities;

import jakarta.persistence.*;
import lombok.*;
import me.elamranioussama.conferenceservice.entities.enums.ConferenceType;
import me.elamranioussama.conferenceservice.models.Keynote;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conference {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String title;
    private ConferenceType type;
    private LocalDate date;
    private Integer durationInMinutes;
    private Integer numberOfRegistrants;
    private Double score;

    @OneToMany(mappedBy = "conference", fetch = FetchType.EAGER)
    private List<Review> reviews;
    private Long keynoteId;
    @Transient
    private Keynote keynote;
}
