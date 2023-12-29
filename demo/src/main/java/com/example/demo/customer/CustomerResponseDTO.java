package com.example.demo.customer;

public record CustomerResponseDTO(Long id, String cnpj, String name, String longitude, String latitude) {
    public CustomerResponseDTO(Customer customer){
        this(customer.getId(), customer.getCnpj(), customer.getName(), customer.getLongitude(), customer.getLatitude());
    }

}
