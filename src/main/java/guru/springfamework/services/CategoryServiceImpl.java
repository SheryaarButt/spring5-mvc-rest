package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDto;
import guru.springfamework.api.v1.model.CategoryListDto;
import guru.springfamework.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryListDto getCategories() {
        return new CategoryListDto(
                        categoryRepository.findAll()
                            .stream()
                            .map(categoryMapper::categoryToCategoryDto)
                            .collect(Collectors.toList()));
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.findByNameIgnoreCase(name));
    }
}
