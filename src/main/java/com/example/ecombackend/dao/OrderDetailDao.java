package com.example.ecombackend.dao;

import com.example.ecombackend.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;


public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {
}
