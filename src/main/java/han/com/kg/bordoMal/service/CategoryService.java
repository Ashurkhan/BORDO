package han.com.kg.bordoMal.service;

import han.com.kg.bordoMal.dto.request.CategoryRequest;
import han.com.kg.bordoMal.dto.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);
}
