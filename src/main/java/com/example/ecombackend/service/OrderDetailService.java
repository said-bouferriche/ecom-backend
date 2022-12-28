package com.example.ecombackend.service;

import com.example.ecombackend.Configuration.JwtRequestFilter;
import com.example.ecombackend.dao.OrderDetailDao;
import com.example.ecombackend.dao.ProductDao;
import com.example.ecombackend.dao.UserDao;
import com.example.ecombackend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private static String ORDER_PLACED = "PLACED";

    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public void placeOrder(OrderInput orderInput){
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
        for (OrderProductQuantity o: productQuantityList){
            Product product = productDao.findById(o.getProductId()).get();

            String current_user = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(current_user).get();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderFullName(orderInput.getFullName());
            orderDetail.setOrderFullOrder(orderInput.getFullAddress());
            orderDetail.setOrderContactNumber(orderInput.getContactNumber());
            orderDetail.setOrderStatus(ORDER_PLACED);
            orderDetail.setOrderAlternateContactNumber(orderInput.getAlternateContactNumber());
            orderDetail.setOrderAmount(product.getProductDiscountedPrice()*o.getQuantity());
            orderDetail.setProduct(product);
            orderDetail.setUser(user);
            orderDetailDao.save(orderDetail);
        }
    }
}
