package net.sparkminds.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.entity.Cart;
import net.sparkminds.entity.User;
import net.sparkminds.repository.CartRepository;
import net.sparkminds.repository.UserRepository;
import net.sparkminds.service.CartService;
import net.sparkminds.service.dto.request.CartRequestDTO;
import net.sparkminds.service.dto.response.CartResponseDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public CartResponseDTO initializeCart(CartRequestDTO cartRequestDTO) {
        User user = userRepository.findById(cartRequestDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User is not exist"));
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setIsDone(0);
        cartRepository.save(cart);
        return CartResponseDTO.builder().id(cart.getId()).userId(cart.getUser().getId()).isDone(cart.getIsDone())
                .total(cart.getTotal()).build();
    }
}
