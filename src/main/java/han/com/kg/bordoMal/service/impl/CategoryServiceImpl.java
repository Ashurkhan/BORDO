package han.com.kg.bordoMal.service.impl;

import han.com.kg.bordoMal.dto.request.CategoryRequest;
import han.com.kg.bordoMal.dto.response.CategoryResponse;
import han.com.kg.bordoMal.mapper.CategoryMapper;
import han.com.kg.bordoMal.model.Category;
import han.com.kg.bordoMal.repository.CategoryRepository;
import han.com.kg.bordoMal.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category category = mapper.toEntity(request);
        Category saved=repository.save(category);
        return mapper.tDto(saved);
    }
}
