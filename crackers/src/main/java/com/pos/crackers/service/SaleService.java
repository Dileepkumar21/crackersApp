package com.pos.crackers.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.crackers.entities.*;
import com.pos.crackers.exception.BusinessException;
import com.pos.crackers.model.Crackers;
import com.pos.crackers.model.Customer;
import com.pos.crackers.model.Sale;
import com.pos.crackers.service.util.SNSUtil;
import com.pos.crackers.service.util.Util;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SaleService {

    @Autowired
    PersistenceService persistenceService;

    @Autowired
    TotalSalesResponse totalSalesResponse;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Util utils;

//    @Transactional
//    public Pair<List<CrackerCost>, Integer> performSale(SaleRequest saleRequest){
//        List<CrackerItem> crackerItemList = saleRequest.getCrackerCostList();
//        List<CrackerCost> crackerCostList = new ArrayList<>();
//        CustomerInfo customerInfo = saleRequest.getCustomerInfo();
//        Customer customer = persistenceService.getCustomer(customerInfo.getName(), customerInfo.getPhoneNumber());
//        Integer totalCost = 0;
//        if(customer==null){
//            customer = persistenceService.saveCustomer(new Customer(customerInfo.getName(), customerInfo.getPhoneNumber()));
//        }
//        Sale sale = new Sale();
//        sale.setCustomer(customer);
//
//        for(CrackerItem crackerItem: crackerItemList){
//            Optional<Crackers> optionalCrackerName = persistenceService.getCrackerByName(crackerItem.getCrackerName());
//            if(optionalCrackerName.isPresent()){
//                Crackers cracker = optionalCrackerName.get();
//                Integer qtyOrdered = crackerItem.getQuantity();
//                if(qtyOrdered > cracker.getQuantityAvailable())
//                    throw new BusinessException(String.format("Quantity available is less than the ordered " +
//                            "quantity for %s. Available quantity %d ", crackerItem.getCrackerName(), cracker.getQuantityAvailable()));
//                Integer crackerCost = qtyOrdered * cracker.getItemPrice();
//                totalCost += crackerCost;
//                updateCrackerTable(crackerItem, cracker, crackerCost, sale);
//                updateSaleTable(sale, cracker, totalCost, crackerCostList);
//                crackerCostList.add(new CrackerCost(crackerItem, crackerCost));
//                persistenceService.saveSale(sale);
//            }
//            else{
//                throw new BusinessException("Cracker not found");
//            }
//        }
//
//        return Pair.of(crackerCostList, totalCost);
//    }

    @Transactional
    public Pair<List<CrackerCost>, Integer> performSale(SaleRequest saleRequest){
        List<CrackerCost> crackerItemList = saleRequest.getCrackerCostList();
        List<CrackerCost> crackerCostList = new ArrayList<>();
        CustomerInfo customerInfo = saleRequest.getCustomerInfo();
        Customer customer = utils.getCustomer(customerInfo);
        Sale sale = new Sale();
        sale.setCustomer(customer);
        Map<Crackers, CrackerCost> crackersItemMap = new HashMap<>();
        for(CrackerCost crackerCostItem: crackerItemList){
            CrackerItem crackerItem = crackerCostItem.getCrackerItem();
            Optional<Crackers> optionalCrackerName = persistenceService.getCrackerByName(crackerItem.getCrackerName());
            if(optionalCrackerName.isPresent()){
                Crackers cracker = optionalCrackerName.get();
                Integer qtyOrdered = crackerItem.getQuantity();
                if(qtyOrdered > cracker.getQuantityAvailable())
                    throw new BusinessException(String.format("Quantity available is less than the ordered " +
                            "quantity for %s. Available quantity %d ", crackerItem.getCrackerName(), cracker.getQuantityAvailable()));
                crackersItemMap.put(cracker, crackerCostItem);
                crackerCostItem.setCost(crackerCostItem.getCost()*crackerCostItem.getCrackerItem().getQuantity());
                crackerCostList.add(crackerCostItem);
            }
            else{
                throw new BusinessException("Cracker not found");
            }
        }
        for(Map.Entry<Crackers, CrackerCost> item: crackersItemMap.entrySet()){
            CrackerCost crackerCost = item.getValue();
            updateCrackerTable(crackerCost.getCrackerItem(), item.getKey(), crackerCost.getCost(), sale);
            updateSaleTable(sale, saleRequest.getActualCost(), saleRequest.getSaleValue(), crackerCostList, item.getKey());
        }
        persistenceService.saveSale(sale);
        //here
        return Pair.of(crackerCostList, saleRequest.getSaleValue());
    }


    public List<Sale> retrieveAllSaleDetails(){
        List<Sale> saleList = persistenceService.getAllSaleDetails();
        return saleList;
    }

    private static void updateSaleTable(Sale sale, Integer actualCost, Integer saleValue, List<CrackerCost> crackerCostList, Crackers cracker) {
        sale.setSaleValue(saleValue);
        sale.setActualCost(actualCost);
        SaleResponse saleResponse = new SaleResponse();
        saleResponse.setCrackerCostList(crackerCostList);
        sale.setSaleResponse(saleResponse);
        sale.addSoldCrackers(cracker);
    }

    private static void updateCrackerTable(CrackerItem crackerItem, Crackers cracker, Integer crackerCost, Sale sale) {
        cracker.setQuantityAvailable(cracker.getQuantityAvailable() - crackerItem.getQuantity());
        cracker.setStockPrice(cracker.getStockPrice() - crackerCost);
        cracker.addToSales(sale);
    }
}
