package net.sparkminds.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.sparkminds.service.dto.request.ProductRequestDTO;
import net.sparkminds.service.dto.response.ProductResponseDTO;

public interface ProductService {
    Page<ProductResponseDTO> getAllProduct(Pageable pageable);
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, long id);
    ProductResponseDTO getProductDetail(Long id);
    List<ProductResponseDTO> getProductByCategoryId(Long id);
    void deleteProduct(Long id);
}
