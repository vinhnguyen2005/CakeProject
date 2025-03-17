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

}
