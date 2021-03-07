package com.learning.spring.boot.Service;

import com.learning.spring.boot.Model.Product;
import com.learning.spring.boot.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> listALl(){
        return repo.findAll();
    }

    public void save(Product product){
        repo.save(product);
    }

    public Product get(long id){
        return repo.findById(id).get();
    }

    public void delete(long id){
        repo.deleteById(id);
    }
}
