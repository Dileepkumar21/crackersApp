package com.pos.crackers.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.crackers.entities.SaleResponse;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;

@Converter(autoApply = true)
public class SaleResponseJsonConverter implements AttributeConverter<SaleResponse, String> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(SaleResponse attribute) {
        try {
            System.out.println(objectMapper.writeValueAsString(attribute));
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting Data to JSON", e);
        }
    }

    @Override
    public SaleResponse convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, SaleResponse.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error reading JSON to Data", e);
        }
    }
}
