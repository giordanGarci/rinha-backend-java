package com.rinha.backend.transactions.services.strategy.impl;

import com.rinha.backend.transactions.entities.Transaction;
import com.rinha.backend.transactions.services.exceptions.ValidationTransactionException;
import com.rinha.backend.transactions.services.strategy.TransactionValidationRequest;

import java.util.Objects;

import static com.rinha.backend.transactions.entities.enums.HttpStatus.UNPROCESSABLE_ENTITY;


public class PositiveValueRequest implements TransactionValidationRequest {
    @Override
    public void validate(Transaction transaction) {
       try{
           Integer.parseInt(transaction.getValor());
       }catch (Exception e){
           throw new ValidationTransactionException(UNPROCESSABLE_ENTITY.getCodigo(), "Campo 'Valor' nulo, com valor negativo ou com valor não inteiro.");
       }
    }
}
