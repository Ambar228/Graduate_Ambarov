package edu.bru.member.microservice.model;

import edu.bru.member.microservice.model.enums.StateEvent;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EVENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column (name = "ID")
    private Long id;

    @Column (name = "NAME")
    private String name;

    @Column (name = "DESCRIPTION")
    private String description;

    @Column (name = "LOCATION")
    private String location;

    @Lob
    private byte[] picture;

    @Column (name = "DATA")
    private Date date;

    @Enumerated(EnumType.STRING)
    private StateEvent state;

    @ManyToOne
    private Type type;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "event_sponsor",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "sponsor_id")
    )
    private List<Sponsor> sponsors;

    @ManyToMany
    @JoinTable(
            name = "event_racer",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "racer_id")
    )
    private List<Racer> racers;

    @OneToOne
    private Tournament tournament;
}
