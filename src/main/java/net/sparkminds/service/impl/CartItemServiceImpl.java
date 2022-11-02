package net.sparkminds.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.entity.Cart;
import net.sparkminds.entity.CartItem;
import net.sparkminds.entity.Product;
import net.sparkminds.repository.CartItemRepository;
import net.sparkminds.repository.CartRepository;
import net.sparkminds.repository.ProductRepository;
import net.sparkminds.service.CartItemService;
import net.sparkminds.service.dto.request.CartItemRequestDTO;
import net.sparkminds.service.dto.response.CartItemResponseDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService{
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    @Override
    @Transactional
    public CartItemResponseDTO createCartItem(CartItemRequestDTO cartItemRequestDTO) {
        Cart cart = cartRepository.findById(cartItemRequestDTO.getCartId()).orElseThrow(() -> new EntityNotFoundException("Cart is not exist"));
        Product product = productRepository.findById(cartItemRequestDTO.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product is not exist"));
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(cartItemRequestDTO.getQuantity());
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setPrice(product.getPrice()*cartItemRequestDTO.getQuantity());
        cartItemRepository.save(cartItem);
        return CartItemResponseDTO.builder()
                .cartId(cartItem.getCart().getId())
                .productId(cartItem.getProduct().getId())
                .price(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .build();
    }
    @Override
    public List<CartItemResponseDTO> getAllItemInCart(Long id) {
        List<CartItem> cartTest = cartItemRepository.getItemByCartId(id);
//        System.out.println("test cart"+cartTest);
        List<CartItem> cartItem = cartRepository.findById(id).orElse(null).getCartItem();
        return cartTest.stream().map(entity -> CartItemResponseDTO
                .builder()
                .cartId(entity.getCart().getId())
                .id(entity.getId())
                .productId(entity.getProduct().getId())
                .price(entity.getPrice())
                .productName(entity.getProduct().getName())
                .quantity(entity.getQuantity())
                .image(entity.getProduct().getImage())
                .build()).collect(Collectors.toList()); 
    }
    @Override
    @Transactional
    public void deleteCartItem(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cart item not found"));
        cartItemRepository.delete(cartItem);
    }
    @Override
    @Transactional
    public void deleteItemByCartId(Long cartId) {
        List<CartItem> cartItem= cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("Cart not found")).getCartItem();
        if (!cartItem.isEmpty()) {
            cartItemRepository.deleteItemByCartId(cartId);
        }
    }

}
