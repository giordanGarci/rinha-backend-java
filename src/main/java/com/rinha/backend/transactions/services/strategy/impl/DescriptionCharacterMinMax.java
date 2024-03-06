package com.rinha.backend.transactions.services.strategy.impl;

import com.rinha.backend.transactions.entities.Transaction;
import com.rinha.backend.transactions.services.exceptions.ValidationTransactionException;
import com.rinha.backend.transactions.services.strategy.TransactionValidationRequest;

import java.util.Objects;

import static com.rinha.backend.transactions.entities.enums.HttpStatus.UNPROCESSABLE_ENTITY;
public class DescriptionCharacterMinMax implements TransactionValidationRequest {
    @Override
    public void validate(Transaction transaction) {
        if (Objects.isNull(transaction.getDescricao())
                 || transaction.getDescricao().length() > 10
                 || transaction.getDescricao().isEmpty()){
            throw new ValidationTransactionException(UNPROCESSABLE_ENTITY.getCodigo(), "Descrição não pode ser nulo e deve ter menos de 10 caracteres");

        }
    }
}
