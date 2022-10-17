package net.sparkminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.sparkminds.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
}
