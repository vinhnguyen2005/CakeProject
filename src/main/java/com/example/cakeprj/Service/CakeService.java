package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.Cake;
import com.example.cakeprj.Repository.CakeProductRepository;
import com.example.cakeprj.dto.request.CakeCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CakeService {

    @Autowired
    private CakeProductRepository cakeRepository;

    public List<Cake> getTopCakesByCategory(String categoryId, int limit, int offset, String sortOrder) {
        List<Cake> cakes = cakeRepository.findTopCakesByCategory(categoryId, limit, offset);


        if ("desc".equalsIgnoreCase(sortOrder)) {
            cakes.sort(Comparator.comparing(Cake::getPrice).reversed());
        } else {
            cakes.sort(Comparator.comparing(Cake::getPrice));
        }

        return cakes;
    }


    public Cake getCakeById(String id) {
        return cakeRepository.findCakeById(id);
    }

    public int getTotalPages(String categoryID, int pageSize) {
        int totalItems = cakeRepository.countCakesByCategory(categoryID);
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    public List<Cake> getCakeProductsById(String categoryId) {
        return cakeRepository.findByCategoryID(categoryId);
    }

    public List<Cake> getAllCakes() { return cakeRepository.findAll(); }

    public boolean existById(String id) { return cakeRepository.existsById(id); }

    public void deleteById(String id) { cakeRepository.deleteById(id); }

    public void updateCake(Cake cake) { cakeRepository.save(cake); }
}
