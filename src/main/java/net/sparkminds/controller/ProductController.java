package net.sparkminds.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.repository.ProductRepository;
import net.sparkminds.service.ProductService;
import net.sparkminds.service.dto.request.ProductParamsRequestDTO;
import net.sparkminds.service.dto.request.ProductRequestDTO;
import net.sparkminds.service.dto.response.ProductResponseDTO;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

//    @GetMapping
//    public ResponseEntity<List<ProductResponseDTO>> getAllProduct(@PageableDefault Pageable pageable,
//            HttpServletResponse response) {
//        long totalElement = productRepository.findAll(pageable).getTotalElements();
//        response.addHeader("X-Total-Count", String.valueOf(totalElement));
//        response.addHeader("Access-Control-Expose-Headers", "*");
//        final Page<ProductResponseDTO> page = productService.getAllProduct(pageable);
//        return ResponseEntity.ok().body(page.getContent());
//    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts(@PageableDefault Pageable pageable,
            HttpServletResponse response, ProductParamsRequestDTO request) {
        final Page<ProductResponseDTO> page = productService.getProducts(pageable, request);
        response.addHeader("X-Total-Count", String.valueOf(page.getSize()));
        response.addHeader("Access-Control-Expose-Headers", "*");
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> getDetailProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductDetail(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.createProduct(productRequestDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductRequestDTO productRequestDTO,
            @PathVariable Long id) {
        return ResponseEntity.ok(productService.updateProduct(productRequestDTO, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "detail")
    public ResponseEntity<List<ProductResponseDTO>> getProductByCategoryId(@RequestParam Long categoryId) {
        return ResponseEntity.ok(productService.getProductByCategoryId(categoryId));
    }
}
