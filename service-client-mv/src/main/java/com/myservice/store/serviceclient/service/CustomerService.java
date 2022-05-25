package com.myservice.store.serviceclient.service;

import java.util.List;

import com.myservice.store.serviceclient.repository.entity.Customer;
import com.myservice.store.serviceclient.repository.entity.Region;

public interface CustomerService {
    
    public List<Customer> findCustomerAll();
    public List<Customer> findCustomersByRegion(Region region);

    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public  Customer getCustomer(Long id);
}
