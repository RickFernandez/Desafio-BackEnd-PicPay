package com.picpaychallenge.persistence.repositories;

import com.picpaychallenge.persistence.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
