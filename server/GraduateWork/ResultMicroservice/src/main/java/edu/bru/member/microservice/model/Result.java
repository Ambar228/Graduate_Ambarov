package edu.bru.member.microservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RESULTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    private Racer racer;

    @ManyToOne
    private Event event;

    private Integer points;
}
