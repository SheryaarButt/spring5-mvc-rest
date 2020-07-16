package guru.springfamework.services;

import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.api.v1.model.CategoryListDto;

public interface CategoryService {
    CategoryListDto getCategories();
    CategoryDto getCategoryByName(String name);
}
