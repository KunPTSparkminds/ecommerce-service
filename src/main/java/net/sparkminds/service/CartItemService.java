package net.sparkminds.service;

import java.util.List;

import net.sparkminds.service.dto.request.CartItemRequestDTO;
import net.sparkminds.service.dto.response.CartItemResponseDTO;

public interface CartItemService {
    CartItemResponseDTO createCartItem(CartItemRequestDTO cartItemRequestDTO);
    List<CartItemResponseDTO> getAllItemInCart(Long id);
    void deleteCartItem(Long id);
    void deleteItemByCartId(Long cartId);
}
