package com.rinha.backend.transactions.services;

import com.rinha.backend.transactions.dtos.TransactionResponseDto;
import com.rinha.backend.transactions.entities.Balance;
import com.rinha.backend.transactions.repositories.BalanceRepository;
import com.rinha.backend.transactions.services.exceptions.ValidationTransactionException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.rinha.backend.transactions.entities.enums.HttpStatus.NOT_FOUND;


@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public Balance findById(Integer id){
        return balanceRepository.findById(id).orElseThrow(() -> new ValidationTransactionException(NOT_FOUND.getCodigo(),"Não foi encontrado saldo com id: " + id));
    }

    @Transactional
    public Balance save(Balance balance){
        return balanceRepository.save(balance);
    }

}
