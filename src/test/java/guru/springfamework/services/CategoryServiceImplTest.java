package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryMapper categoryMapper;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCategories() {
        List<Category> categories = new ArrayList<>();
        Category category1 = Category.builder().id(1L).name("Test1").build();
        Category category2 = Category.builder().id(2L).name("Test2").build();
        categories.add(category1);
        categories.add(category2);
        CategoryDto categoryDto1 = CategoryDto.builder().id(1L).name("Test1").build();
        CategoryDto categoryDto2 = CategoryDto.builder().id(2L).name("Test2").build();
        when(categoryMapper.categoryToCategoryDto(category1)).thenReturn(categoryDto1);
        when(categoryMapper.categoryToCategoryDto(category2)).thenReturn(categoryDto2);

        when(categoryRepository.findAll()).thenReturn(categories);

        assertEquals(2,categoryService.getCategories().getCategories().size());
    }

    @Test
    public void getCategoryByName() {
        Category category1 = Category.builder().id(1L).name("Test1").build();
        CategoryDto categoryDto1 = CategoryDto.builder().id(1L).name("Test1").build();
        when(categoryMapper.categoryToCategoryDto(category1)).thenReturn(categoryDto1);

        when(categoryRepository.findByNameIgnoreCase("Test1")).thenReturn(category1);

        assertEquals("Test1",categoryService.getCategoryByName("Test1").getName());
    }
}