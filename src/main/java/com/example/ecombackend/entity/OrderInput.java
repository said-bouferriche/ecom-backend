package com.example.ecombackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderInput {
    @Id
    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private String alternateContactNumber;
//    @ManyToMany
    private List<OrderProductQuantity> orderProductQuantityList;
}
