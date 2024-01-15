package com.picpaychallenge.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserRepository, Long> {
    Optional<UserRepository> findUserByDocument(String document);
    Optional<UserRepository> findUserById(Long id);
}
