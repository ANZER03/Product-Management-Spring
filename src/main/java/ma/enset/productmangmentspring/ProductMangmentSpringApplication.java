package ma.enset.productmangmentspring;

import ma.enset.productmangmentspring.entities.AppUser;
import ma.enset.productmangmentspring.entities.Product;
import ma.enset.productmangmentspring.repo.ProductRepository;
import ma.enset.productmangmentspring.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class ProductMangmentSpringApplication {




    public static void main(String[] args) {
        SpringApplication.run(ProductMangmentSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository, UserRepository userRepository, PasswordEncoder encoder){
        return args -> {
            productRepository.save(Product.builder().name("Laptop").price(1000).quantity(10).build());
            productRepository.save(Product.builder().name("Phone").price(500).quantity(20).build());
            productRepository.save(Product.builder().name("Tablet").price(300).quantity(30).build());

            productRepository.findAll().forEach(System.out::println);

//            userRepository.save(new AppUser().builder().username("admin").password(encoder.encode("1234")).roles(Arrays.asList("ADMIN" , "USER")).build());
//            userRepository.save(new AppUser().builder().username("user1").password(encoder.encode("1234")).roles(List.of("USER")).build());
        };
    }

}
