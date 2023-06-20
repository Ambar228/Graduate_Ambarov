package edu.bru.sponsormicroservce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TYPES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
}
