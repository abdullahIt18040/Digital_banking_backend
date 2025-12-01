package com.sil.digitalbankingbackend.services;

import com.sil.digitalbankingbackend.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private ProductService productService;
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public void placeOrder(Integer productId,Integer quantity){

       Product product= productService.getProductById(productId);
       if(quantity>product.getStock()){

           throw new RuntimeException("quantity exceeds stock");

       }

    }


}
