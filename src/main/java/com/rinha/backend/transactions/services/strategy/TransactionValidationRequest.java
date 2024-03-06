package com.rinha.backend.transactions.services.strategy;

import com.rinha.backend.transactions.entities.Transaction;

public interface TransactionValidationRequest {
    void validate(Transaction transaction);
}
