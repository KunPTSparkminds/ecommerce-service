package net.sparkminds.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.entity.Category;
import net.sparkminds.repository.CategoryRepository;
import net.sparkminds.service.CategoryService;
import net.sparkminds.service.dto.request.CategoryRequestDTO;
import net.sparkminds.service.dto.response.CategoryResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTO> getAllCategory() {
        // TODO Auto-generated method stub
        return categoryRepository.findAll().stream()
                .map(entity -> CategoryResponseDTO.builder().id(entity.getId()).name(entity.getName())
                        .description(entity.getDescription()).image(entity.getImage()).createdAt(entity.getCreatedAt())
                        .updateAt(entity.getUpdateAt()).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setImage(categoryRequestDTO.getImage());
        categoryRepository.save(category);
        return CategoryResponseDTO.builder().id(category.getId()).name(category.getName())
                .description(category.getDescription()).image(category.getImage()).createdAt(category.getCreatedAt())
                .updateAt(category.getUpdateAt()).build();
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.delete(category);
        }
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category is not exist"));
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setImage(categoryRequestDTO.getImage());
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .image(category.getImage())
                .updateAt(category.getUpdateAt())
                .createdAt(category.getCreatedAt())
                .build();
    }

    @Override
    public CategoryResponseDTO getCategoryDetail(Long id) {
        // TODO Auto-generated method stub
        return categoryRepository.findById(id).map(entity -> CategoryResponseDTO.builder().id(entity.getId()).name(entity.getName())
                .description(entity.getDescription()).image(entity.getImage()).createdAt(entity.getCreatedAt())
                .updateAt(entity.getUpdateAt()).build()).orElseThrow(() -> new EntityNotFoundException("Category is not exist"));
    }



}
