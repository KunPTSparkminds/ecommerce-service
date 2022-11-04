package net.sparkminds.service.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductParamsRequestDTO {
    private Long productId;
    private Long categoryId;
}
