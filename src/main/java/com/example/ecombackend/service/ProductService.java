package com.example.ecombackend.service;

import com.example.ecombackend.dao.ProductDao;
import com.example.ecombackend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product addNewProduct(Product product){
        return productDao.save(product);
    }

    public List<Product> getAllProduct(){
        return (List<Product>) productDao.findAll();
    }

    public void deleteProduct(Integer productId){
        productDao.deleteById(productId);
    }
}
