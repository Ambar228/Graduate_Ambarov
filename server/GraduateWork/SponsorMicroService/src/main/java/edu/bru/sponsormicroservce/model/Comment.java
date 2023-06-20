package edu.bru.sponsormicroservce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "ID")
    private Long id;

    @Column (name = "TEXT")
    private String text;

    @Column (name = "DATE")
    private LocalDateTime date;

    @Lob
    private byte[] file;

    @Column(name = "CHANGED")
    private Boolean changed;

    @ManyToOne
    private User user;

    @JsonIgnore
    @JoinColumn(name = "event_id")
    @ManyToOne
    private Event event;
}
