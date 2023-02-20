package com.example.productService.services;


import com.example.productService.model.enteties.Product;
import com.example.productService.model.enteties.ProductImage;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.example.productService.repositories.ProductRepo;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@EnableCaching
public class ProductService {


    private ProductRepo productRepo;


    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    //Add a product to the database
    public Product save(Product product)
    {
        return productRepo.save(product);
    }



    @Cacheable(key = "#id",value ="Product")
    public Product getOneProduct(long id){
        return productRepo.findById(id).get();
    }


    //Get all products from the database
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    //Delete a product from the database
    public void deleteProduct(long id){
        productRepo.deleteById(id);
    }

    //create image in database
    public String createImage(ProductImage productImage) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = db.collection("productImages").document(productImage.getImage()).set(productImage);
        return future.get().getUpdateTime().toString();
    }

}
