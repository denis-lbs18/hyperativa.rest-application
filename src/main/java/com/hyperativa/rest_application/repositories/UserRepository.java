package com.hyperativa.rest_application.repositories;

import com.hyperativa.rest_application.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
