package com.rinha.backend.transactions.services;

import com.rinha.backend.transactions.entities.Balance;
import com.rinha.backend.transactions.entities.Transaction;
import com.rinha.backend.transactions.entities.User;
import com.rinha.backend.transactions.repositories.TransactionRepository;
import com.rinha.backend.transactions.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StatementService {

    private final TransactionRepository transactionRepository;
    private final BalanceService balanceService;
    private final TransactionService transactionService;
    public User getUserWithTransactions(Integer userId){
        User user = transactionService.findUserById(userId);
        user.setLastTransactions(findLast10TransactionsByUser(user));
        Balance balance = balanceService.findById(user.getBalance().getId());
        balance.setDataExtrato(LocalDateTime.now());
        balanceService.save(balance);
        return user;
    }

    public List<Transaction> findLast10TransactionsByUser(User user) {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("realizadaEm").descending());
        Page<Transaction> page = transactionRepository.findByUserOrderByRealizadaEmDesc(user, pageRequest);
        return page.getContent();
    }
}
