package net.sparkminds.service.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemRequestDTO {
    private Long quantity;
    private Long productId;
    private Long cartId;
}
