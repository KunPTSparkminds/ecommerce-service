package net.sparkminds.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDTO {
    private Long id;
    private Long cartId;
    private Long price;
    private Long productId;
    private String productName;
    private String image;
    private Long quantity;
}
