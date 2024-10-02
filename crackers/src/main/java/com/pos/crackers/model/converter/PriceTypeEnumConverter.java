package com.pos.crackers.model.converter;

import com.pos.crackers.enums.PriceTypeEnum;
import jakarta.persistence.AttributeConverter;

public class PriceTypeEnumConverter implements AttributeConverter<PriceTypeEnum, String> {


    @Override
    public String convertToDatabaseColumn(PriceTypeEnum itemTypeEnum) {
        return itemTypeEnum == null ? null : itemTypeEnum.getCode();
    }

    @Override
    public PriceTypeEnum convertToEntityAttribute(String s) {
        return s == null ? null : PriceTypeEnum.get(s);
    }
}
