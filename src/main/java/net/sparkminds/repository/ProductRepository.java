package net.sparkminds.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.sparkminds.entity.Product;
import net.sparkminds.service.dto.request.ProductParamsRequestDTO;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByCategoryId(Long id);
    
    @Query(value = "SELECT p FROM Product p "
            + "WHERE 1 = 1 "
            + "AND (:#{#request.productId} IS NULL OR p.id = :#{#request.productId})"
            + "AND (:#{#request.categoryId} IS NULL OR p.category.id = :#{#request.categoryId})")
    Page<Product> findAllProductWithPagination(ProductParamsRequestDTO request, Pageable pageable);
}
