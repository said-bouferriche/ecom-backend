package com.example.ecombackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductQuantity {
    @Id
    private Integer id;
    private Integer productId;
    private Integer quantity;
}
