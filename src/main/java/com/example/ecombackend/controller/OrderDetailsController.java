package com.example.ecombackend.controller;

import com.example.ecombackend.entity.OrderInput;
import com.example.ecombackend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailsController {

    @Autowired
   private OrderDetailService orderDetailService;

   @PreAuthorize("hasRole('User')")
   @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrderInput orderInput){
       orderDetailService.placeOrder(orderInput);
    }
}
