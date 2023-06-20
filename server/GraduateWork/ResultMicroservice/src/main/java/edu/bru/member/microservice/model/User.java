package edu.bru.member.microservice.model;

import edu.bru.member.microservice.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID", nullable = false)
    private Long id;

    @Column (name = "NAME")
    private String name;

    @Column (name = "LASTNAME")
    private String lastname;

    @Column (name = "NUMBER_PHONE")
    private String numberPhone;

    @Column (name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
