package com.iqmsoft.crm.restfulwebservices.service;

import org.springframework.stereotype.Service;

import com.iqmsoft.crm.restfulwebservices.model.Customer;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerService {
    private static List<Customer> customers;
    private static long idCounter = 0;

    static {
        customers = new LinkedList<>(Arrays.asList(
                new Customer(++idCounter, "test1", new Date(), "test1@iqmsoft.com"),
                new Customer(++idCounter, "test2", new Date(), "test2@iqmsoft.com"),
                new Customer(++idCounter, "test3", new Date(), "test3@iqmsoft.com")
        ));
    }

    public List<Customer> findAll() {
        return customers;
    }

    public Customer save(Customer customer) {
        if(customer.getId() < 1) {
            customer.setId(++idCounter);
            customers.add(customer);
        } else {
            deleteById(customer.getId());
            customers.add(customer);
        }

        return customer;
    }

    public Customer deleteById(long id) {
        Customer customer = findById(id);

        if(customer == null) return null;

        if(customers.remove(customer))
            return customer;
        return null;
    }

    public Customer findById(long id) {
        for(Customer customer : customers)
            if(customer.getId() == id)
                return customer;
        return null;
    }
}
