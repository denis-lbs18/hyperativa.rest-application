package com.hyperativa.rest_application.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cards", indexes = {@Index(columnList = "number", name = "idx_card_number")})
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, unique = true, length = 64)
    private String number;

    @Column(name = "charge")
    private String charge;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public Card(String number) {
        this.number = number;
    }

    public Card(String number, String charge) {
        this.number = number;
        this.charge = charge;
    }
}