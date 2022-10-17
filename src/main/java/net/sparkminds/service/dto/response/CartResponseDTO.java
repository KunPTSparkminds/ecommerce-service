package net.sparkminds.service.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartResponseDTO {
    private Long id;
    private Long userId;
    private Long total;
    private Integer isDone;
}
