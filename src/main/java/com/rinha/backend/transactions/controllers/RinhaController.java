package com.rinha.backend.transactions.controllers;

import com.rinha.backend.transactions.dtos.TransactionResponseDto;
import com.rinha.backend.transactions.entities.Transaction;
import com.rinha.backend.transactions.entities.User;
import com.rinha.backend.transactions.services.BalanceService;
import com.rinha.backend.transactions.services.StatementService;
import com.rinha.backend.transactions.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clientes")
@Validated
@RequiredArgsConstructor
public class RinhaController {

    private final TransactionService transactionService;
    private final StatementService statementService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user) {
        transactionService.createUser(user);
        return ResponseEntity.ok().body("Usuário criado com sucesso.");
    }

    @GetMapping(value = "/{id}/extrato")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        User user = statementService.getUserWithTransactions(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/{id}/transacoes")
    public ResponseEntity<?> create(@Valid @RequestBody Transaction transaction, @PathVariable Integer id) {
        transactionService.save(transaction, id);
        TransactionResponseDto responseDto = transactionService.objToDto(transaction.getUser().getBalance());
        return ResponseEntity.ok().body(responseDto);
    }

}
