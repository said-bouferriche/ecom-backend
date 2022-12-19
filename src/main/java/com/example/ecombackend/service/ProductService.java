package com.example.ecombackend.service;

import com.example.ecombackend.dao.ProductDao;
import com.example.ecombackend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product addNewProduct(Product product){
        return productDao.save(product);
    }
}
