package com.pos.crackers.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.pos.crackers.entities.CrackerCost;
import com.pos.crackers.entities.SaleRequest;
import com.pos.crackers.entities.TotalSalesResponse;
import com.pos.crackers.exception.BusinessException;
import com.pos.crackers.mapper.ResponseEntityMapper;

import com.pos.crackers.model.Crackers;
import com.pos.crackers.model.Sale;

import com.pos.crackers.service.PersistenceService;
import com.pos.crackers.service.SaleService;
import com.pos.crackers.validation.RequestValidator;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrackersSaleAPI {

    @Autowired
    PersistenceService persistenceService;

    @Autowired
    ResponseEntityMapper responseEntityMapper;

    @Autowired
    RequestValidator requestValidator;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SaleService saleService;

    @GetMapping(produces = "application/json", path = "/")
    public ResponseEntity<String> check(){
        return new ResponseEntity("OK",HttpStatusCode.valueOf(200));
    }

    @GetMapping(produces = "application/json", path = "/healthCheck")
    public ResponseEntity<String> ping(){
        return new ResponseEntity("OK",HttpStatusCode.valueOf(200));
    }

    @GetMapping(produces = "application/json", path = "/crackers-api/getCrackersList")
    public ResponseEntity<String> getCrackersList(){
        List<Crackers> crackers = persistenceService.fetchALlCrackers();
        String applicationResponse  = responseEntityMapper.mapCrackersResponse(crackers);
        return new ResponseEntity(applicationResponse, HttpStatusCode.valueOf(200));
    }

//    @PostMapping(path = "/performsale", produces = "application/json")
//    public ResponseEntity<String> performSale(@RequestBody SaleRequest saleRequest) throws JsonProcessingException {
//
//        String response = null;
//        ResponseEntity responseEntity = null;
//        try {
//            requestValidator.validateSaleRequest(saleRequest);
//            Pair<List<CrackerCost>, Integer> performSaleResponse = saleService.performSale(saleRequest);
//            String applicationResponse = responseEntityMapper.mapSaleResponse(performSaleResponse);
//            return new ResponseEntity(applicationResponse, HttpStatusCode.valueOf(200));
//
//        } catch (BusinessException exp) {
//            response = responseEntityMapper.mapErrorResponse(400 ,exp.getMessage());
//            responseEntity = new ResponseEntity(response, HttpStatusCode.valueOf(400));
//        }
//        return responseEntity;
//    }

    @GetMapping(produces = "application/json", path = "/crackers-api/getSaleList")
    public ResponseEntity<String> getALlSaleDetails(){
        String response = null;
        ResponseEntity responseEntity = null;
        try {
            List<Sale> sales = saleService.retrieveAllSaleDetails();
            TotalSalesResponse totalSalesResponse = responseEntityMapper.totalSalesMapper(sales);
            response = objectMapper.writeValueAsString(totalSalesResponse);
        }catch (BusinessException exp) {
            response = responseEntityMapper.mapErrorResponse(400 ,exp.getMessage());
            responseEntity = new ResponseEntity(response, HttpStatusCode.valueOf(400));
        }catch (Exception exp){
            response = responseEntityMapper.mapErrorResponse(500, exp.getMessage());
            responseEntity = new ResponseEntity(response, HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity(response, HttpStatusCode.valueOf(200));
    }

    @PostMapping(path = "/crackers-api/performsale", produces = "application/json")
    public ResponseEntity<String> performSale(@RequestBody SaleRequest saleRequest) throws JsonProcessingException {

        String response = null;
        ResponseEntity responseEntity = null;
        try {
            requestValidator.validateSaleRequest(saleRequest);
            Pair<List<CrackerCost>, Integer> performSaleResponse = saleService.performSale(saleRequest);
            String applicationResponse = responseEntityMapper.mapSaleResponse(performSaleResponse);
            return new ResponseEntity(applicationResponse, HttpStatusCode.valueOf(200));

        } catch (BusinessException exp) {
            response = responseEntityMapper.mapErrorResponse(400 ,exp.getMessage());
            responseEntity = new ResponseEntity(response, HttpStatusCode.valueOf(400));
        }
        return responseEntity;
    }

    @PostMapping(path = "/crackers-api/submitorder", produces = "application/json")
    public ResponseEntity<String> submitOrder(@RequestBody SaleRequest saleRequest) throws JsonProcessingException {

        String response = null;
        ResponseEntity responseEntity = null;
        String submitorder = null;
        try {
            submitorder = saleService.submitorder(saleRequest);
        } catch (Exception exp) {

        }

        return new ResponseEntity(submitorder, HttpStatusCode.valueOf(200));
    }

}
