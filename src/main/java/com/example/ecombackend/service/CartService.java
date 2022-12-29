package com.example.ecombackend.service;

import com.example.ecombackend.Configuration.JwtRequestFilter;
import com.example.ecombackend.dao.CartDao;
import com.example.ecombackend.dao.ProductDao;
import com.example.ecombackend.dao.UserDao;
import com.example.ecombackend.entity.Cart;
import com.example.ecombackend.entity.Product;
import com.example.ecombackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId){
        Product product = productDao.findById(productId).get();
        String username = JwtRequestFilter.CURRENT_USER;

        User user = null;
        if (username !=null){
            user = userDao.findById(username).get();
        }


        if (product!=null && username!=null){
            Cart cart = new Cart(null,product,user);
            return cartDao.save(cart);

        }
        return null;
    }
}
