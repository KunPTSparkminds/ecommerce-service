package net.sparkminds.service.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartRequestDTO {
    private Long userId;
}   
