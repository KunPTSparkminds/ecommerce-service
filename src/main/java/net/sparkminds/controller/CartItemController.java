package net.sparkminds.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.CartItemService;
import net.sparkminds.service.dto.request.CartItemRequestDTO;
import net.sparkminds.service.dto.response.CartItemResponseDTO;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;
    
    @PostMapping("/add-item")
    public ResponseEntity<CartItemResponseDTO> createCartItem(@RequestBody CartItemRequestDTO cartItemRequestDTO) {
        return ResponseEntity.ok(cartItemService.createCartItem(cartItemRequestDTO));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("id") Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    public ResponseEntity<?> deleteItemByCartId(@RequestParam Long cartId) {
        cartItemService.deleteItemByCartId(cartId);
        return ResponseEntity.noContent().build();
    }
}
