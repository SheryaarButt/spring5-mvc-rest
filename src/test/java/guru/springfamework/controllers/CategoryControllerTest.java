package guru.springfamework.controllers;

import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.api.v1.model.CategoryListDto;
import guru.springfamework.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategoryControllerTest {

    public static final String TEST_NAME = "TestName";
    public static final long TEST_ID = 1L;
    public static final long TEST_ID_2 = 2L;

    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void getCategories() {

        CategoryListDto categories = new CategoryListDto(Arrays.asList(CategoryDto.builder().id(TEST_ID).build()
                                                                      ,CategoryDto.builder().id(TEST_ID_2).build()));

        when(categoryService.getCategories()).thenReturn(categories);

        try{
            mockMvc.perform(get("/api/v1/categories"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("$.categories",hasSize(2)));
        } catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void getCategoryByName() {
        CategoryDto category = CategoryDto.builder().id(TEST_ID).name(TEST_NAME).build();

        when(categoryService.getCategoryByName(TEST_NAME)).thenReturn(category);

        try{
            mockMvc.perform(get("/api/v1/categories/TestName"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("$.name", equalTo(TEST_NAME)));
        } catch(Exception e){
            fail(e.getMessage());
        }
    }
}