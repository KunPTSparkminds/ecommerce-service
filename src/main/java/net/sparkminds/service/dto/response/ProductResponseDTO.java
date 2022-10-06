package net.sparkminds.service.dto.response;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponseDTO {
    private long id;
    private String name;
    private String description;
    private Long price;
    private Long quantity;
    private Long categoryId;
    private String categoryName;
    private Date updateAt;
    private Date createdAt;
}
