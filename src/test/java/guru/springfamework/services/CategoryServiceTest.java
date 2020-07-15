package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;
    @Mock
    CategoryMapper categoryMapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository,categoryMapper);
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


        when(categoryRepository.findAll()).thenReturn(categories);
        when(categoryMapper.categoryToCategoryDto(category1)).thenReturn(categoryDto1);
        when(categoryMapper.categoryToCategoryDto(category2)).thenReturn(categoryDto2);

        assertEquals(2,categoryService.getCategories().size());
        verify(categoryMapper,times(2)).categoryToCategoryDto(any());
    }

    @Test
    public void getCategoryByName() {

        Category category1 = Category.builder().id(1L).name("Test1").build();
        CategoryDto categoryDto1 = CategoryDto.builder().id(1L).name("Test1").build();

        when(categoryRepository.findByName("Test1")).thenReturn(category1);
        when(categoryMapper.categoryToCategoryDto(category1)).thenReturn(categoryDto1);

        assertEquals("Test1",categoryService.getCategoryByName("Test1").getName());
        verify(categoryMapper,times(1)).categoryToCategoryDto(any());

    }
}