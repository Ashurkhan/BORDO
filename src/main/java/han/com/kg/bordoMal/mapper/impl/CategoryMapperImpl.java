package han.com.kg.bordoMal.mapper.impl;

import han.com.kg.bordoMal.dto.request.CategoryRequest;
import han.com.kg.bordoMal.dto.response.CategoryResponse;
import han.com.kg.bordoMal.mapper.CategoryMapper;
import han.com.kg.bordoMal.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public CategoryResponse tDto(Category category) {
        CategoryResponse response=new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }

    @Override
    public List<CategoryResponse> tDtos(List<Category> categories) {
    List<CategoryResponse>  responses= new ArrayList<>();
    for(Category category:categories){
        responses.add(tDto(category));
    }
    return responses;
    }

    @Override
    public Category toEntity(CategoryRequest request) {
        Category category=new Category();
        category.setName(request.getName());
        return category;
    }
}
