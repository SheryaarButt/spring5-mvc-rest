package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    CategoryRepository categoryRepository;
    CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
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

        Customer customer1 = Customer.builder().firstName("Johnny").lastName("Whatevs").build();
        Customer customer2 = Customer.builder().firstName("Tiff").lastName("Jerky").build();
        Customer customer3 = Customer.builder().firstName("Gerb").lastName("Ballrz").build();
        Customer customer4 = Customer.builder().firstName("Lammy").lastName("Tonktonk").build();
        Customer customer5 = Customer.builder().firstName("Sigma").lastName("Tetris").build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);

        List<Customer> customers = customerRepository.findAll();

        if(customers.size() < 5){
            throw new Exception("Bootstrap failed to save customers to DB");
        }

        System.out.println("The following customers are in the database: ");
        customerRepository.findAll().forEach(customer ->
                System.out.println(customer.getId() + " " + customer.getFirstName() + customer.getLastName()));

    }
}
