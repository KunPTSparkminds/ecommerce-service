package net.sparkminds.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.CartItemService;
import net.sparkminds.service.CartService;
import net.sparkminds.service.dto.request.CartRequestDTO;
import net.sparkminds.service.dto.response.CartItemResponseDTO;
import net.sparkminds.service.dto.response.CartResponseDTO;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    
    @PostMapping
    private ResponseEntity<CartResponseDTO> initializeCart(@RequestBody CartRequestDTO cartRequestDTO) {
        return ResponseEntity.ok(cartService.initializeCart(cartRequestDTO));
    }
    
    @GetMapping(value="/{id}")
    private ResponseEntity<List<CartItemResponseDTO>> getItemInCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartItemService.getAllItemInCart(id));
    }
    
}
