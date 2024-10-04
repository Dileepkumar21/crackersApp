package com.pos.crackers.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pos.crackers.model.Crackers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.endpoints.internal.Value;

import java.util.List;

@JsonPropertyOrder({"totalValue","crackersList","apiStatus"})
@Getter
@Setter
@Builder
@Component
public class CrackersResponse {

    private ApiStatus apiStatus;
    private List<Crackers> crackersList;
    private Integer totalValue;

    public CrackersResponse(ApiStatus apiStatus, List<Crackers> crackers, Integer totalValue) {
        this.apiStatus = apiStatus;
        this.crackersList = crackers;
        this.totalValue = totalValue;
    }

    public CrackersResponse(){

    }
}
