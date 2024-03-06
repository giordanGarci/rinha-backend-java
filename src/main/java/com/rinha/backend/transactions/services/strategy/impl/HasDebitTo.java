package com.rinha.backend.transactions.services.strategy.impl;

import com.rinha.backend.transactions.entities.Transaction;
import com.rinha.backend.transactions.entities.enums.TypeEnum;
import com.rinha.backend.transactions.services.exceptions.ValidationTransactionException;
import com.rinha.backend.transactions.services.strategy.TransactionValidationRequest;

import java.util.Objects;

import static com.rinha.backend.transactions.entities.enums.HttpStatus.UNPROCESSABLE_ENTITY;


public class HasDebitTo implements TransactionValidationRequest {

    @Override
    public void validate(Transaction transaction) {
        if (Objects.equals(transaction.getTipo(), TypeEnum.DEBIT)) {
            if (!isDebitTransactionValid(transaction)) {
                throw new ValidationTransactionException(UNPROCESSABLE_ENTITY.getCodigo(),"Do not have limit to do this transaction");
            }
        }
    }


    /**
     * Verifica se a transação de débito é válida com base no saldo total, valor da transação e limite.
     *
     * @param transaction Transação a ser validada.
     * @return verdadeiro se a transação de débito for válida, falso caso contrário.
     */
    private boolean isDebitTransactionValid(Transaction transaction) {
        try {
            Integer totalBalance = transaction.getUser().getBalance().getTotal();
            Integer transactionAmount = Integer.parseInt(transaction.getValor());
            Integer limit = transaction.getUser().getBalance().getLimite();
            return verifyDebit(totalBalance, transactionAmount, limit);
        }catch (Exception e){
            throw new ValidationTransactionException(UNPROCESSABLE_ENTITY.getCodigo(), "Valor deve ser inteiro");
        }
    }

    /**
     * Verifica se o débito não excede o limite do saldo negativo.
     *
     * @param totalBalance Saldo total atual.
     * @param transactionAmount Valor da transação de débito.
     * @param limit Limite para o saldo negativo.
     * @return verdadeiro se o saldo após a transação for maior ou igual ao limite negativo, falso caso contrário.
     */
    private boolean verifyDebit(Integer totalBalance, Integer transactionAmount, Integer limit) {
        return (totalBalance - transactionAmount >= -limit);
    }
}
