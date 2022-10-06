package net.sparkminds.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.entity.Category;
import net.sparkminds.repository.CategoryRepository;
import net.sparkminds.service.CategoryService;
import net.sparkminds.service.dto.request.CategoryRequestDTO;
import net.sparkminds.service.dto.response.CategoryResponseDTPO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTPO> getAllCategory() {
        // TODO Auto-generated method stub
        return categoryRepository.findAll().stream()
                .map(entity -> CategoryResponseDTPO.builder().id(entity.getId()).name(entity.getName())
                        .description(entity.getDescription()).createdAt(entity.getCreatedAt())
                        .updateAt(entity.getUpdateAt()).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponseDTPO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        categoryRepository.save(category);
        return CategoryResponseDTPO.builder().id(category.getId()).name(category.getName())
                .description(category.getDescription()).createdAt(category.getCreatedAt())
                .updateAt(category.getUpdateAt()).build();
    }

}
