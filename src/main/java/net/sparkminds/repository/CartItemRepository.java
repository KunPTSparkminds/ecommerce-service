package net.sparkminds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.sparkminds.entity.CartItem;
import net.sparkminds.service.dto.response.CartItemResponseDTO;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Modifying
    @Query(value = "delete from cart_item where cart_item.cart_id =:cartId", nativeQuery = true)
    void deleteItemByCartId(Long cartId);

    @Query(value = "SELECT SUM(quantity) as quantity, SUM(price) as price, cart_id, product_id,id FROM cart_item WHERE cart_item.cart_id =:cartId GROUP BY cart_id, product_id", nativeQuery = true)
    List<CartItem> getItemByCartId(Long cartId);
}
