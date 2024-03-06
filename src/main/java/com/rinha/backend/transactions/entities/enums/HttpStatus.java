package com.rinha.backend.transactions.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpStatus {

        NOT_FOUND(404, "Not Found"),

        UNPROCESSABLE_ENTITY(422, "Unprocessable Entity");

        private final int codigo;

        private final String descricao;
}
