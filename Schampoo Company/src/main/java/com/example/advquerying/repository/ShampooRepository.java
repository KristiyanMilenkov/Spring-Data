package com.example.advquerying.repository;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long>{
    List<Shampoo> findByBrand (String brand);
    List<Shampoo> findAllByBrandAndSize(String brand, Size size);

    List<Shampoo> findAllBySizeOrderById(Size size);


    @Query("SELECT s FROM Shampoo s" +
            " JOIN s.ingredients AS i" +
            " WHERE i.name IN :ingredientNames")
    List<Shampoo> findByIngredientsNames(Set<String> ingredientNames);

    List<Shampoo> findBySize(Size size);

    List<Shampoo> findBySizeOrLabelIdOrderByPriceAsc(Size size, long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size < :count")
    List<Shampoo> findByIngredientsCountLessThan(int count);
}
