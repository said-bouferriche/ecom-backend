package com.example.ecombackend.controller;

import com.example.ecombackend.entity.ImageModel;
import com.example.ecombackend.entity.Product;
import com.example.ecombackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = "/addNewProduct", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product,
                                 @RequestPart("imageFile") MultipartFile[] file){
        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewProduct(product);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("deleteProductDetails/{productId}")
    public void deleteProductDetails(@PathVariable("productId") Integer productId){
        productService.deleteProduct(productId);
    }

    @GetMapping("/getProductDetailsById/{productId}")
    public Product getProductDetailsById(@PathVariable("productId") Integer productId){
        return productService.getProductById(productId);
    }

    @GetMapping("/getProductDetails/{isSingleProductCheckout}/{productId}")
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
                                           @PathVariable(name = "productId") Integer productId){

        return productService.getProductDetails(isSingleProductCheckout, productId);
    }



    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for(MultipartFile file: multipartFiles){
            ImageModel imageModel = new ImageModel(
//                    file.getOriginalFilename(),
//                    file.getContentType(),
//                    file.getBytes()
            );
            imageModel.setOriginalFilename(file.getOriginalFilename());
            imageModel.setContentType(file.getContentType());
            imageModel.setBytes(file.getBytes());
            imageModels.add(imageModel);
        }
        return imageModels;
    }
}
