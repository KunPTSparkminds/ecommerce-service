package net.sparkminds.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
    public Page<ProductResponseDTO> getAllProduct(Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        Page<Product> totalElement = productRepository.findAll(pageable);
        headers.set("X-Total-Count", Long.toString(totalElement.getTotalElements()));
        return productRepository.findAll(pageable)
                .map(entity -> ProductResponseDTO.builder().id(entity.getId()).name(entity.getName())
                        .description(entity.getDescription()).price(entity.getPrice()).quantity(entity.getQuantity())
                        .image(entity.getImage()).categoryId(entity.getCategory().getId())
                        .categoryName(entity.getCategory().getName()).createdAt(entity.getCreatedAt())
                        .updateAt(entity.getUpdateAt()).build());
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
        product.setImage(productRequestDTO.getImage());
        product.setCategory(category);
        productRepository.save(product);
        return ProductResponseDTO.builder().id(product.getId()).name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).quantity(product.getQuantity())
                .image(product.getImage()).createdAt(product.getCreatedAt()).updateAt(product.getUpdateAt())
                .categoryId(category.getId()).categoryName(category.getName()).build();
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, long id) {
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElse(null);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product is not exist"));
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setImage(productRequestDTO.getImage());
        product.setCategory(category);
        return ProductResponseDTO.builder().id(product.getId()).name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).quantity(product.getQuantity())
                .image(product.getImage()).createdAt(product.getCreatedAt()).updateAt(product.getUpdateAt())
                .categoryId(category.getId()).categoryName(category.getName()).build();
    }

    @Override
    @Transactional
    public ProductResponseDTO getProductDetail(Long id) {
        return productRepository.findById(id)
                .map(entity -> ProductResponseDTO.builder().id(entity.getId()).name(entity.getName())
                        .description(entity.getDescription()).price(entity.getPrice()).quantity(entity.getQuantity())
                        .image(entity.getImage()).categoryId(entity.getCategory().getId())
                        .categoryName(entity.getCategory().getName()).createdAt(entity.getCreatedAt())
                        .updateAt(entity.getUpdateAt()).build())
                .orElseThrow(() -> new EntityNotFoundException("Product is not exist"));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.delete(product);
        }
    }

    @Override
    public List<ProductResponseDTO> getProductByCategoryId(Long id) {
        return productRepository.findByCategoryId(id).stream()
                .map(entity -> ProductResponseDTO.builder().id(entity.getId()).name(entity.getName())
                        .description(entity.getDescription()).price(entity.getPrice()).quantity(entity.getQuantity())
                        .image(entity.getImage())
                        .createdAt(entity.getCreatedAt())
                        .updateAt(entity.getUpdateAt())
                        .categoryId(entity.getCategory().getId())
                        .categoryName(entity.getCategory().getName())
                        .build())
                .collect(Collectors.toList());
    }
}
