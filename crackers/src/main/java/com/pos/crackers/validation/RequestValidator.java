package com.pos.crackers.validation;

import com.pos.crackers.entities.CrackerItem;
import com.pos.crackers.entities.CustomerInfo;
import com.pos.crackers.entities.SaleRequest;
import com.pos.crackers.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestValidator {

    public void validateSaleRequest(SaleRequest saleRequest){
        ObjectNotNull(saleRequest);
        CustomerInfo customerInfo = saleRequest.getCustomerInfo();
        List<CrackerItem> crackerItemList = saleRequest.getCrackerItemList();
        ObjectNotNull(customerInfo);
        ListObjectNotEmpty(crackerItemList);
        validateCustomerInfo(customerInfo);
        validateCrackerItemList(crackerItemList);

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

    private void ListObjectNotEmpty(List<CrackerItem> crackerItemList) {
        if(crackerItemList.isEmpty())
            throw new BusinessException("Cracker List is Empty");
    }

    private void ObjectNotNull(Object object) {
        if(object == null)
            throw new BusinessException(object.getClass().toString()+ " is null");
    }
}
