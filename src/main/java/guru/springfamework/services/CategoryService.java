package guru.springfamework.services;

import guru.springfamework.api.v1.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategories();
    CategoryDto getCategoryByName(String name);
}
