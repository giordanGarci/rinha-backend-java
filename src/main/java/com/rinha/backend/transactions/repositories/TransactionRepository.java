package com.rinha.backend.transactions.repositories;

import com.rinha.backend.transactions.entities.Transaction;
import com.rinha.backend.transactions.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Page<Transaction> findByUserOrderByRealizadaEmDesc(User user, Pageable pageable);
}
