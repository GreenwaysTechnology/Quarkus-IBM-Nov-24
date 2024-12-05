package com.refactorizando.sample.saga.usecase;

import java.time.LocalDate;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    private String surname;

    private LocalDate birth;

    private String email;

    @OneToOne
    private Seat seat;


}
