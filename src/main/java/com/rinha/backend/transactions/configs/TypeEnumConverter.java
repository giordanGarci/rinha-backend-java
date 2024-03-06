package com.rinha.backend.transactions.configs;

import com.rinha.backend.transactions.entities.enums.TypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TypeEnumConverter implements AttributeConverter<TypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(TypeEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDescricao();
    }

    @Override
    public TypeEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return TypeEnum.toEnum(dbData);
    }
}
