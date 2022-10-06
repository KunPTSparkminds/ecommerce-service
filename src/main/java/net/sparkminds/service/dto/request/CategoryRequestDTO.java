package net.sparkminds.service.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryRequestDTO {
    @NotBlank(message = "The name is required")
    private String name;
    
    @NotBlank(message = "The description is required")
    private String description;
}
