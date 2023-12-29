package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import com.example.demo.customer.CustomerRequestDTO;
import com.example.demo.customer.CustomerResponseDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping
    public void saveCustomer(@RequestBody CustomerRequestDTO data){
        Customer customerData = new Customer(data);
        repository.save(customerData);
        return;
    }

    @PatchMapping("{id}")
    public void editCustomer(@PathVariable("id") Long id, @RequestBody Customer data){
        Optional<Customer> customer =  repository.findById(id);
        if (!customer.isEmpty())
            repository.save(data);
        return;
    }

    @GetMapping
    public List<CustomerResponseDTO> getAll(){
        List<CustomerResponseDTO> customerList = repository.findAll().stream().map(CustomerResponseDTO::new).toList();
        return customerList;
    }

    @GetMapping("{id}")
    public CustomerResponseDTO getOne(@PathVariable("id") Long id){
        Optional<Customer> customer =  repository.findById(id);
        if (!customer.isEmpty())
            return new CustomerResponseDTO(
                customer.get().getId(), 
                customer.get().getCnpj(),
                customer.get().getName(),
                customer.get().getLongitude(),
                customer.get().getLatitude()
                );

        return null;
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        repository.deleteById(id);
    }

}
