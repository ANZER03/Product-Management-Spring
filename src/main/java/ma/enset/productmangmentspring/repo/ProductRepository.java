package ma.enset.productmangmentspring.repo;

import ma.enset.productmangmentspring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByNameContainingIgnoreCase(String name);
}
