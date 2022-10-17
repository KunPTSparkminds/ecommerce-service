package net.sparkminds.service.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long quantity;
    private Long categoryId;
    private String categoryName;
    private String image;
    private Date updateAt;
    private Date createdAt;
}
