package net.sparkminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sparkminds.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
