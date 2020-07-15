package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDto() {
        //when
        Category category = new Category();
        category.setId(1L);
        category.setName("sup");
        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(category);
        assertEquals(category.getId(),categoryDto.getId());
        assertEquals(category.getName(),categoryDto.getName());
    }

    @Test
    public void categoryDtoToCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("sup");
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        assertEquals(categoryDto.getId(),category.getId());
        assertEquals(categoryDto.getName(),category.getName());
    }
}