package com.pos.crackers.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.crackers.entities.*;
import com.pos.crackers.model.Crackers;
import com.pos.crackers.model.Customer;
import com.pos.crackers.model.Sale;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResponseEntityMapper {

    @Autowired
    ObjectMapper objectMapper;

    public String mapCrackersResponse(List<Crackers> crackers){
        ApiStatus apiStatus = new ApiStatus();
        apiStatus.setCode(200);
        apiStatus.setReason("OK");
        CrackersResponse crackersResponse = new CrackersResponse(apiStatus, crackers, calculateTotalValue(crackers));
        String response = null;
        try {
          response = objectMapper.writeValueAsString(crackersResponse);
        }
        catch (JsonProcessingException exp){
            throw new RuntimeException("JsonProcessingException");
        }
        return response;
    }

    private Integer calculateTotalValue(List<Crackers> crackers) {
        return crackers.stream().mapToInt(val -> val.getStockPrice()).sum();
    }

    public String mapErrorResponse(Integer code, String reason) {
        ApiStatus apiStatus = new ApiStatus();
        apiStatus.setCode(code);
        apiStatus.setReason(reason);
        String response = null;
        try {
            response = objectMapper.writeValueAsString(apiStatus);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public String mapSaleResponse(Pair<List<CrackerCost>, Integer> performSaleResponse) {

        SaleResponse saleResponse = new SaleResponse();
        saleResponse.setCrackerCostList(performSaleResponse.getLeft());
        saleResponse.setSaleValue(performSaleResponse.getRight());
        String response = null;
        try {
            response = objectMapper.writeValueAsString(saleResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public TotalSalesResponse totalSalesMapper(List<Sale> totalSaleList){
        TotalSalesResponse totalSalesResponse = new TotalSalesResponse();
        List<SaleItem> saleItemList = new ArrayList<>();
        totalSaleList = totalSaleList.stream().sorted( (o1, o2) -> o2.getCreateTs().compareTo(o1.getCreateTs())).toList();
        for(Sale sale : totalSaleList){
            Customer customer = sale.getCustomer();
            SaleItem saleItem = new SaleItem(new CustomerInfo(customer.getCustName(), customer.getMobileNum()), sale.getSaleResponse());
            saleItemList.add(saleItem);
        }
        totalSalesResponse.setSaleItemList(saleItemList);
        totalSalesResponse.setTotalSaleValue(totalSaleList.stream().mapToInt(item -> item.getSaleValue()).sum());
        return totalSalesResponse;
    }
}
