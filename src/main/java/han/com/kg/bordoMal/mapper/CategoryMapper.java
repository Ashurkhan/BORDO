package han.com.kg.bordoMal.mapper;

import han.com.kg.bordoMal.dto.request.CategoryRequest;
import han.com.kg.bordoMal.dto.response.CategoryResponse;
import han.com.kg.bordoMal.model.Category;

import java.util.List;

public interface CategoryMapper {
    CategoryResponse tDto(Category category);
    List<CategoryResponse> tDtos(List<Category> categories);
    Category toEntity(CategoryRequest request);
}
