package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryMapperTest {

    @Autowired
    CategoryMapper categoryMapper;

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