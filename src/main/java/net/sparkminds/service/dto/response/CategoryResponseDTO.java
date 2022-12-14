package net.sparkminds.service.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private long id;
    private String name;
    private String description;
    private String image;
    private Date updateAt;
    private Date createdAt;
}
