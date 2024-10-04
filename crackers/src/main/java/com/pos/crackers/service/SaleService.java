package com.pos.crackers.service;

import com.pos.crackers.entities.*;
import com.pos.crackers.exception.BusinessException;
import com.pos.crackers.model.Crackers;
import com.pos.crackers.model.Customer;
import com.pos.crackers.model.Sale;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SaleService {

    @Autowired
    PersistenceService persistenceService;

    @Autowired
    TotalSalesResponse totalSalesResponse;

    @Transactional
    public Pair<List<CrackerCost>, Integer> performSale(SaleRequest saleRequest){
        List<CrackerItem> crackerItemList = saleRequest.getCrackerItemList();
        List<CrackerCost> crackerCostList = new ArrayList<>();
        CustomerInfo customerInfo = saleRequest.getCustomerInfo();
        Customer customer = persistenceService.getCustomer(customerInfo.getName(), customerInfo.getPhoneNumber());
        Integer totalCost = 0;
        if(customer==null){
            customer = persistenceService.saveCustomer(new Customer(customerInfo.getName(), customerInfo.getPhoneNumber()));
        }
        Sale sale = new Sale();
        sale.setCustomer(customer);

        for(CrackerItem crackerItem: crackerItemList){
            Optional<Crackers> optionalCrackerName = persistenceService.getCrackerByName(crackerItem.getCrackerName());
            if(optionalCrackerName.isPresent()){
                Crackers cracker = optionalCrackerName.get();
                Integer qtyOrdered = crackerItem.getQuantity();
                if(qtyOrdered > cracker.getQuantityAvailable())
                    throw new BusinessException(String.format("Quantity available is less than the ordered " +
                            "quantity for %s. Available quantity %d ", crackerItem.getCrackerName(), cracker.getQuantityAvailable()));
                Integer crackerCost = qtyOrdered * cracker.getItemPrice();
                totalCost += crackerCost;
                updateCrackerTable(crackerItem, cracker, crackerCost, sale);
                updateSaleTable(sale, cracker, totalCost, crackerCostList);
                crackerCostList.add(new CrackerCost(crackerItem, crackerCost));
                persistenceService.saveSale(sale);
            }
            else{
                throw new BusinessException("Cracker not found");
            }
        }

        return Pair.of(crackerCostList, totalCost);
    }

    public List<Sale> retrieveAllSaleDetails(){
        List<Sale> saleList = persistenceService.getAllSaleDetails();
        return saleList;
    }

    private static void updateSaleTable(Sale sale, Crackers cracker, Integer totalCost, List<CrackerCost> crackerCostList) {
        sale.addSoldCrackers(cracker);
        sale.setSellValue(totalCost);
        SaleResponse saleResponse = new SaleResponse();
        saleResponse.setCrackerCostList(crackerCostList);
        sale.setSaleResponse(saleResponse);
    }

    private static void updateCrackerTable(CrackerItem crackerItem, Crackers cracker, Integer crackerCost, Sale sale) {
        cracker.setQuantityAvailable(cracker.getQuantityAvailable() - crackerItem.getQuantity());
        cracker.setStockPrice(cracker.getStockPrice() - crackerCost);
        cracker.addToSales(sale);
    }
}
