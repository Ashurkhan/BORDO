package han.com.kg.bordoMal.controller;

import han.com.kg.bordoMal.dto.request.CategoryRequest;
import han.com.kg.bordoMal.dto.response.CategoryResponse;
import han.com.kg.bordoMal.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("create")
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CategoryRequest request){
        CategoryResponse response=service.create(request);
        return ResponseEntity.ok(response);
    }

}
