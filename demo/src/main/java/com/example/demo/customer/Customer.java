package com.example.demo.customer;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "customer")
@Entity(name = "customer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private String name;
    private String longitude;
    private String latitude;

    public Customer(CustomerRequestDTO data){
        this.cnpj = data.cnpj();
        this.name = data.name();
        this.longitude = data.longitude();
        this.latitude = data.latitude();
    }
}
