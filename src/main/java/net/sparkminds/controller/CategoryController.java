package net.sparkminds.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.CategoryService;
import net.sparkminds.service.dto.request.CategoryRequestDTO;
import net.sparkminds.service.dto.response.CategoryResponseDTO;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    
    
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryDetail(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryDetail(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequestDTO));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@RequestBody CategoryRequestDTO categoryRequestDTO,@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryRequestDTO, id));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
