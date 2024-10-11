package com.pos.crackers.validation;

import com.pos.crackers.entities.CrackerCost;
import com.pos.crackers.entities.CrackerItem;
import com.pos.crackers.entities.CustomerInfo;
import com.pos.crackers.entities.SaleRequest;
import com.pos.crackers.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestValidator {

//    public void validateSaleRequest(SaleRequest saleRequest){
//        ObjectNotNull(saleRequest);
//        CustomerInfo customerInfo = saleRequest.getCustomerInfo();
//        List<CrackerItem> crackerItemList = saleRequest.getCrackerCostList();
//        ObjectNotNull(customerInfo);
//        ListObjectNotEmpty(crackerItemList);
//        validateCustomerInfo(customerInfo);
//        validateCrackerItemList(crackerItemList);
//
//    }

    public void validateSaleRequest(SaleRequest saleRequest){
        objectNotNull(saleRequest);
        CustomerInfo customerInfo = saleRequest.getCustomerInfo();
        List<CrackerCost> crackerCostItemList = saleRequest.getCrackerCostList();
        objectNotNull(customerInfo);
        ListObjectNotEmpty(crackerCostItemList);
        validateCustomerInfo(customerInfo);
        validateCrackerCostList(crackerCostItemList);
        objectNotNull(saleRequest.getTotalCost());

    }

    private void validateCrackerCostList(List<CrackerCost> crackerCostItemList) {
        for(CrackerCost crackerCost: crackerCostItemList){
            if(crackerCost.getCrackerItem().getCrackerName() == null)
                throw new BusinessException("Cracker name cannot be null");
            if(crackerCost.getCrackerItem().getQuantity() == null)
                throw new BusinessException("cracker cost is null");
            if(crackerCost.getCost() == null)
                throw new BusinessException("cost is null");
        }
    }

    private void validateCrackerItemList(List<CrackerItem> crackerItemList) {
        for(CrackerItem item: crackerItemList){
            if(item.getCrackerName() == null)
                throw new BusinessException("Cracker name cannot be null");
            if(item.getQuantity() == null)
                throw new BusinessException("cracker quantity is null");
        }
    }

    private void validateCustomerInfo(CustomerInfo customerInfo) {
        if(customerInfo.getName() == null)
            throw new BusinessException("Customer name is null");
        if(customerInfo.getPhoneNumber() == null)
            throw new BusinessException("Customer phone number is null");
    }

    private void ListObjectNotEmpty(List<?> objectList) {
        if(objectList.isEmpty())
            throw new BusinessException("Cracker Cost list is Empty");
    }

    private void objectNotNull(Object object) {
        if(object == null)
            throw new BusinessException("object is null");
    }
}
