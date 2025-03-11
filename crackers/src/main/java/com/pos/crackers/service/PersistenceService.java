package com.pos.crackers.service;

import com.pos.crackers.model.Crackers;
import com.pos.crackers.model.Customer;
import com.pos.crackers.model.Sale;
import com.pos.crackers.repo.CrackersRepository;
import com.pos.crackers.repo.CustomerRepository;
import com.pos.crackers.repo.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersistenceService {

    @Autowired
    CrackersRepository crackersRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SaleRepository saleRepository;

    public List<Crackers> fetchALlCrackers(){
        return crackersRepository.findAll();
    }

    public Customer getCustomer(String name, String phoneNumber) {
        Optional<Customer> customerData = customerRepository.findByCustNameAndMobileNum(name, phoneNumber);
        return customerData.isPresent()?customerData.get():null;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Crackers> getCrackerByName(String crackerName) {
         return crackersRepository.findByName(crackerName);
    }

    public void saveSale(Sale sale) {
        saleRepository.save(sale);
    }

    public void saveCrackers(Crackers crackers){
        crackersRepository.save(crackers);
    }

    public List<Sale> getAllSaleDetails() {
        return saleRepository.findAll();
    }
}
