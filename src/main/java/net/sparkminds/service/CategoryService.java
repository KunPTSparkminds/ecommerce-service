package net.sparkminds.service;

import java.util.List;

import net.sparkminds.service.dto.request.CategoryRequestDTO;
import net.sparkminds.service.dto.response.CategoryResponseDTPO;

public interface CategoryService {
    List<CategoryResponseDTPO> getAllCategory();
    
    CategoryResponseDTPO createCategory(CategoryRequestDTO categoryRequestDTO);
}
