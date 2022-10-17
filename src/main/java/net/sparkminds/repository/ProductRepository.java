package net.sparkminds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sparkminds.entity.Product;
import net.sparkminds.service.dto.response.ProductResponseDTO;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByCategoryId(Long id);
}
