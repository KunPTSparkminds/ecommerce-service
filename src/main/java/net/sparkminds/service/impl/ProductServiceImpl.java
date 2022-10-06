package net.sparkminds.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.entity.Category;
import net.sparkminds.entity.Product;
import net.sparkminds.repository.CategoryRepository;
import net.sparkminds.repository.ProductRepository;
import net.sparkminds.service.ProductService;
import net.sparkminds.service.dto.request.ProductRequestDTO;
import net.sparkminds.service.dto.response.ProductResponseDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> getAllProduct() {
        return productRepository.findAll().stream().map(entity -> ProductResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .categoryId(entity.getCategory().getId())
                .categoryName(entity.getCategory().getName())
                .createdAt(entity.getCreatedAt())
                .updateAt(entity.getUpdateAt())
                .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElse(null);
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setCategory(category);
        productRepository.save(product);
        return ProductResponseDTO.builder().id(product.getId()).name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).quantity(product.getQuantity())
                .createdAt(product.getCreatedAt()).updateAt(product.getUpdateAt()).categoryId(category.getId())
                .categoryName(category.getName()).build();
    }

}
