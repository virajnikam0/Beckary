package com.pkg.services;

import com.pkg.models.Category;
import com.pkg.models.Product;
import com.pkg.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product){
        productRepository.save(product);
    }
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public void deleteByIdProduct(long id){
        productRepository.deleteById(id);
    }
    public Optional<Product> updateProductById(long id){
        return productRepository.findById(id);
    }

    public List<Product> findAllByCategoryId(int id){
        return productRepository.findAllByCategory_Id(id);
    }



}
