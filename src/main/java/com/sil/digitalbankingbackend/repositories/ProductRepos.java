package com.sil.digitalbankingbackend.repositories;

import com.sil.digitalbankingbackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepos extends JpaRepository<Product,Integer> {
}
