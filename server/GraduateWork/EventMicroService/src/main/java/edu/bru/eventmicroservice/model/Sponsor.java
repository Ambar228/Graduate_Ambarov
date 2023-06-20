package edu.bru.eventmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "SPONSORS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private byte[] logo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADVERTISING_INFORMATION")
    private String advertisingInformation;

    @Column(name = "CONTACTS")
    private String contacts;

    @ManyToMany(mappedBy = "sponsors")
    @ToString.Exclude
    @JsonIgnore
    private List<Event> events;
}
