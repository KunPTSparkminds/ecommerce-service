package net.sparkminds.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.sparkminds.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByCategoryId(Long id);
}
