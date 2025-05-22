package ma.enset.productmangmentspring.web;

import jakarta.validation.Valid;
import ma.enset.productmangmentspring.entities.Product;
import ma.enset.productmangmentspring.repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        return "add-product";
    }

    @PostMapping("/products/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-product";
        }
        productRepository.save(product);
        return "redirect:/products";
    }

    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
}
