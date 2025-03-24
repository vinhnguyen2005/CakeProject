package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.Cake;
import com.example.cakeprj.Entity.OrderDetails;
import com.example.cakeprj.Repository.CakeProductRepository;
import com.example.cakeprj.Repository.OrderDetailsRepository;
import com.example.cakeprj.dto.request.CakeCreationRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CakeService {

    @Autowired
    private CakeProductRepository cakeRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;


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

    @Transactional
    public void deleteById(String id) {
        List<OrderDetails> relatedDetails = orderDetailsRepository.findByCakeId(id);
        for (OrderDetails detail : relatedDetails) {
            detail.setCake(null);
        }
        orderDetailsRepository.saveAll(relatedDetails);
        cakeRepository.deleteById(id);
    }

    public void updateCake(Cake cake) { cakeRepository.save(cake); }

    public List<Cake> findCakeFromSearch(String searchText) {
        return cakeRepository.findByNameContainingIgnoreCase(searchText);
    }

    public List<Cake> getTopSellingCakes(int limit){
        return cakeRepository.findTopSellingCakes(limit);
    }

    public List<Cake> getCakesByCategory(String categoryID, int limit) {
        return cakeRepository.getCakesByCategory(categoryID, limit);
    }

    public List<Cake> getLatestCake(int limit) {
        return cakeRepository.findTopNewestCakes(limit);
    }

    public List<Cake> getRandomCakes() {
        return cakeRepository.findRandomCakes();
    }

    public void deleteByCategoryId(String categoryId) {
        List<Cake> cakes = cakeRepository.findByCategoryID(categoryId);
        if (!cakes.isEmpty()) {
            cakeRepository.deleteAll(cakes);
        }
    }


}
