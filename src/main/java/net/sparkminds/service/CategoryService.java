package net.sparkminds.service;

import java.util.List;
import java.util.Optional;

import net.sparkminds.service.dto.request.CategoryRequestDTO;
import net.sparkminds.service.dto.response.CategoryResponseDTO;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategory();
    
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO, Long id);
    CategoryResponseDTO getCategoryDetail(Long id);
    void deleteCategory(Long id);
}
