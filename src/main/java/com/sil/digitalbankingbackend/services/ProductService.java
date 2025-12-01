package com.sil.digitalbankingbackend.services;

import com.sil.digitalbankingbackend.entities.Product;
import com.sil.digitalbankingbackend.repositories.ProductRepos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    private ProductRepos productRepos;
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Product getProductById(Integer id)
    {
        return productRepos.findById(id).orElseThrow(()->
                new RuntimeException("product not found"));
    }

}
