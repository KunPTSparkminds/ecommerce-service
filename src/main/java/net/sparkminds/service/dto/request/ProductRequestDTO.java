package net.sparkminds.service.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductRequestDTO {
    private String name;
    private String description;
    private Long price;
    private Long quantity;
    private Long categoryId;
}
