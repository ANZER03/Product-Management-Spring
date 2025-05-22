package ma.enset.productmangmentspring.repo;

import ma.enset.productmangmentspring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
