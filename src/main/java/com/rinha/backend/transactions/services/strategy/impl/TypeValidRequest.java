package com.rinha.backend.transactions.services.strategy.impl;

import com.rinha.backend.transactions.entities.Transaction;
import com.rinha.backend.transactions.services.exceptions.ValidationTransactionException;
import com.rinha.backend.transactions.services.strategy.TransactionValidationRequest;

import java.util.Objects;

import static com.rinha.backend.transactions.entities.enums.HttpStatus.UNPROCESSABLE_ENTITY;

public class TypeValidRequest implements TransactionValidationRequest {
    @Override
    public void validate(Transaction transaction) {
        if (Objects.isNull(transaction.getTipo())
                || !(transaction.getTipo().getDescricao().equalsIgnoreCase("d"))
                && !(transaction.getTipo().getDescricao().equalsIgnoreCase("c"))){
            throw new ValidationTransactionException(UNPROCESSABLE_ENTITY.getCodigo(), "Tipo da transacao invalido ou nulo");
        }
    }
}
