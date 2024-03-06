package com.rinha.backend.transactions.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.rinha.backend.transactions.services.exceptions.ValidationTransactionException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

import static com.rinha.backend.transactions.entities.enums.HttpStatus.UNPROCESSABLE_ENTITY;

@AllArgsConstructor
@Getter
public enum TypeEnum {
    CREDIT("c"),
    DEBIT( "d");

    private final String descricao;

    public static TypeEnum toEnum(String typeParam) {
        if (Objects.isNull(typeParam)) {
            throw new ValidationTransactionException(UNPROCESSABLE_ENTITY.getCodigo(), "Tipo da transação não pode ser nulo.");
        }

        return Arrays.stream(TypeEnum.values())
                .filter(e -> typeParam.equals(e.getDescricao()))
                .findFirst()
                .orElseThrow(() -> new ValidationTransactionException(UNPROCESSABLE_ENTITY.getCodigo(), "Tipo da transação inválido: " + typeParam));
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TypeEnum forValue(String value) {
        for (TypeEnum type : TypeEnum.values()) {
            if (type.getDescricao().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new ValidationTransactionException(UNPROCESSABLE_ENTITY.getCodigo(), "Tipo inválido: " + value);
    }

}
