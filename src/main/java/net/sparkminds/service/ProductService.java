package net.sparkminds.service;

import java.util.List;

import net.sparkminds.service.dto.request.ProductRequestDTO;
import net.sparkminds.service.dto.response.ProductResponseDTO;

public interface ProductService {
    List<ProductResponseDTO> getAllProduct();
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
}
