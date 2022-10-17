package net.sparkminds.service;

import net.sparkminds.service.dto.request.CartRequestDTO;
import net.sparkminds.service.dto.response.CartResponseDTO;

public interface CartService {
    CartResponseDTO initializeCart(CartRequestDTO cartRequestDTO);
}
