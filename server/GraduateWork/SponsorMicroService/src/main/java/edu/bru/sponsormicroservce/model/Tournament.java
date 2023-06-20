package edu.bru.sponsormicroservce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TOURNAMENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;
}
