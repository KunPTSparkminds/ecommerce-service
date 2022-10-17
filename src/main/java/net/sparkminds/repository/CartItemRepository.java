package net.sparkminds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.sparkminds.entity.CartItem;
import net.sparkminds.service.dto.response.CartItemResponseDTO;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    @Query(value="select * from cart a, cart_item b where a.id = b.cart_id", nativeQuery = true)
    List<CartItemResponseDTO> getItemInCartById(Long id);
}
