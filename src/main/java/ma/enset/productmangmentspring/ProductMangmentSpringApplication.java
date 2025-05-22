package ma.enset.productmangmentspring;

import ma.enset.productmangmentspring.entities.Product;
import ma.enset.productmangmentspring.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@SpringBootApplication
public class ProductMangmentSpringApplication {




    public static void main(String[] args) {
        SpringApplication.run(ProductMangmentSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder().name("Laptop").price(1000).quantity(10).build());
            productRepository.save(Product.builder().name("Phone").price(500).quantity(20).build());
            productRepository.save(Product.builder().name("Tablet").price(300).quantity(30).build());

            productRepository.findAll().forEach(System.out::println);

        };
    }

}
