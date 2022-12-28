package com.example.ecombackend.service;

import com.example.ecombackend.dao.ProductDao;
import com.example.ecombackend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product addNewProduct(Product product){
        return productDao.save(product);
    }

    public List<Product> getAllProduct(int pageNumber, String searchKey){
        Pageable pageable = PageRequest.of(pageNumber,5);
        if (searchKey.equals("")){
            return (List<Product>) productDao.findAll(pageable);
        }else {
            return (List<Product>) productDao.findProductByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey,pageable);
        }
    }

    public void deleteProduct(Integer productId){
        productDao.deleteById(productId);
    }

    public Product getProductById(Integer productId){
        return productDao.findById(productId).get();
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId){
        if (isSingleProductCheckout){
            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;
        }
        else {
            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;
        }
    }
}
