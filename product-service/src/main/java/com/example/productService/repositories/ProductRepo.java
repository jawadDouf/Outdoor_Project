package com.example.productService.repositories;

import com.example.productService.model.enteties.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface ProductRepo extends JpaRepository<Product,Long> {

}
