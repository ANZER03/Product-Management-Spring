package ma.enset.productmangmentspring.web;

import ma.enset.productmangmentspring.entities.Product;
import ma.enset.productmangmentspring.repo.ProductRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/user/index")
    public String index(Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "Products";
    }

    @PostMapping("/admin/delete")
    public String deleteProduct(@RequestParam(name = "id") Long id){
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }





    @GetMapping("/products")
    public List<Product> listProducts(){
        return productRepository.findAll();
    }
}
