package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Category category1 = Category.builder().name("Fruits").build();
        Category category2 = Category.builder().name("Dried").build();
        Category category3 = Category.builder().name("Fresh").build();
        Category category4 = Category.builder().name("Exotic").build();
        Category category5 = Category.builder().name("Nuts").build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);

        List<Category> categories = categoryRepository.findAll();

        if(categories.size() < 5){
            throw new Exception("Bootstrap failed to save categories to DB");
        }

        System.out.println("The following categories are in the database: ");
        categoryRepository.findAll().forEach(category ->
                System.out.println(category.getId() + " " + category.getName()));

    }
}
