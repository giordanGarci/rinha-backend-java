package com.rinha.backend.transactions.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationTransactionException extends RuntimeException{
    private final int statusCode;
    private final String message;
}
