package com.rinha.backend.transactions.services;

import com.rinha.backend.transactions.dtos.TransactionResponseDto;
import com.rinha.backend.transactions.entities.Balance;
import com.rinha.backend.transactions.entities.Transaction;
import com.rinha.backend.transactions.entities.User;
import com.rinha.backend.transactions.repositories.TransactionRepository;
import com.rinha.backend.transactions.repositories.UserRepository;
import com.rinha.backend.transactions.services.exceptions.ValidationTransactionException;
import com.rinha.backend.transactions.services.strategy.TransactionValidationRequest;
import com.rinha.backend.transactions.services.strategy.impl.DescriptionCharacterMinMax;
import com.rinha.backend.transactions.services.strategy.impl.HasDebitTo;
import com.rinha.backend.transactions.services.strategy.impl.PositiveValueRequest;
import com.rinha.backend.transactions.services.strategy.impl.TypeValidRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static com.rinha.backend.transactions.entities.enums.HttpStatus.NOT_FOUND;


@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BalanceService balanceService;
    private final UserRepository userRepository;

    private final Set<TransactionValidationRequest> validationsDataFromRequestSet = Set.of(
            new TypeValidRequest(),
            new PositiveValueRequest(),
            new DescriptionCharacterMinMax(),
            new HasDebitTo());

    @Transactional
    public void save(Transaction obj, Integer id) {
        User user = findUserById(id);
        obj.setUser(user);

        validateTransaction(obj);

        Balance balance = user.getBalance();
        balance.setTotal(balance.getTotal() - Integer.parseInt(obj.getValor())); // débito reduz o saldo

        balanceService.save(balance);
        obj.setRealizadaEm(LocalDateTime.now());
        transactionRepository.save(obj);
    }

    public void validateTransaction(Transaction transaction) {
        validationsDataFromRequestSet.forEach(imp -> imp.validate(transaction));
    }

    @Transactional
    public void createUser(User newUser) {
        userRepository.save(newUser);
    }

    public User findUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(obj -> obj.getBalance().setDataExtrato(
                LocalDateTime.now()
        ));
        return user.orElseThrow(() -> new ValidationTransactionException(NOT_FOUND.getCodigo(),
                "Usuário não encontrado! Id: " + id
        ));
    }

    public TransactionResponseDto objToDto(Balance obj) {
        return new TransactionResponseDto(obj.getLimite(), obj.getTotal());
    }
}
