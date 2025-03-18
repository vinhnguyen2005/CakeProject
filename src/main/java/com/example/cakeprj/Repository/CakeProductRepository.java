package com.example.cakeprj.Repository;

import com.example.cakeprj.Entity.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CakeProductRepository extends JpaRepository<Cake, String> {

    @Query(value = "SELECT * FROM cakeproduct c " +
            "JOIN cake_categories cc ON c.id = cc.cakeid " +
            "WHERE cc.categoryid = :categoryID", nativeQuery = true)
    List<Cake> findByCategoryID(@Param("categoryID") String categoryID);

    @Query("SELECT COUNT(c) FROM Cake c JOIN c.categories cat WHERE cat.id = :categoryID")
    int countCakesByCategory(@Param("categoryID") String categoryID);

    @Query(value = "SELECT * FROM cakeproduct c WHERE c.id = :id", nativeQuery = true)
    Cake findCakeById(@Param("id") String id);

    @Query(value = "SELECT * FROM cakeproduct c " +
            "JOIN cake_categories cc ON c.id = cc.cakeid " +
            "WHERE cc.categoryid = :categoryID " +
            "LIMIT :size OFFSET :startIndex", nativeQuery = true)
    List<Cake> findTopCakesByCategory(@Param("categoryID") String categoryID,
                                      @Param("size") int size,
                                      @Param("startIndex") int startIndex);

    List<Cake> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT c.* FROM cakeproduct c " +
            "JOIN orderdetails od ON c.id = od.cake_id " +
            "GROUP BY c.id " +
            "ORDER BY SUM(od.quantity) DESC " +
            "LIMIT :limit", nativeQuery = true)
    List<Cake> findTopSellingCakes(@Param("limit") int limit);


    @Query(value = "SELECT * FROM cakeproduct c " +
            "JOIN cake_categories cc ON c.id = cc.cakeid " +
            "WHERE cc.categoryid = :categoryID " +
            "ORDER BY c.price ASC LIMIT :limit", nativeQuery = true)
    List<Cake> getCakesByCategory(@Param("categoryID") String categoryID, @Param("limit") int limit);

    @Query(value = "SELECT * FROM cakeproduct ORDER BY created_at DESC LIMIT :limit", nativeQuery = true)
    List<Cake> findTopNewestCakes(@Param("limit") int limit);

    @Query(value = "SELECT * FROM cakeproduct ORDER BY RAND() LIMIT 4", nativeQuery = true)
    List<Cake> findRandomCakes();


}



