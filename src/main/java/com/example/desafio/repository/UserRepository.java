package com.example.desafio.repository;

import com.example.desafio.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
    boolean existsByEmail(String email);
    Optional<UserModel> findByEmail(String email);
}
