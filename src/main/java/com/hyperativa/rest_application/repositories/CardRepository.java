package com.hyperativa.rest_application.repositories;

import com.hyperativa.rest_application.entities.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Long> {
    Optional<Card> findByNumber(String number);
    boolean existsByNumber(String number);
}