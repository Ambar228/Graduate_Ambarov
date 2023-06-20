package edu.bru.commentmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "RACERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Racer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID", nullable = false)
    private Long id;

    @Column (name = "NAME")
    private String name;

    @Column (name = "LASTNAME")
    private String lastname;

    @ManyToMany(mappedBy = "racers")
    @ToString.Exclude
    @JsonIgnore
    private List<Event> events;
}
