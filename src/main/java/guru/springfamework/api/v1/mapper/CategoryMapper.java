package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.domain.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryDto categoryToCategoryDto(Category category);
    Category categoryDtoToCategory(CategoryDto categoryDto);
}
