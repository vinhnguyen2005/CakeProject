package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.Cake;
import com.example.cakeprj.Repository.CakeProductRepository;
import com.example.cakeprj.dto.request.CakeCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeService {

    @Autowired
    private CakeProductRepository repository;

    public List<Cake> getTopCakesByCategory(String categoryId, int limit, int offset) {
        return repository.findTopCakesByCategory(categoryId, limit, offset);
    }

    public Cake getCakeById(String id) {
        return repository.findCakeById(id);
    }

    public int getTotalPages(String categoryID, int pageSize) {
        int totalItems = repository.countCakesByCategory(categoryID);
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    public List<Cake> getCakeProductsById(String categoryId) {
        return repository.findByCategoryID(categoryId);
    }

    public List<Cake> getAllCakes() { return repository.findAll(); }

    public boolean existById(String id) { return repository.existsById(id); }

}
