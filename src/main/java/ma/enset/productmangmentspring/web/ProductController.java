package ma.enset.productmangmentspring.web;

import jakarta.servlet.http.HttpSession;
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
        return "redirect:/user/index";
    }

    @GetMapping("/user/index")
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/user/search")
    public String productSearch(@RequestParam(name="name", required = false) String name, Model model) {

        List<Product> products;
        if (name != null && !name.trim().isEmpty()){
            products = productRepository.findProductByNameContainingIgnoreCase(name);
        }else{
            products = productRepository.findAll();
        }

        model.addAttribute("products", products);

        return "products";
    }


    @GetMapping("/admin/newProduct")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @GetMapping("/admin/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        return "add-product";
    }

    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-product";
        }
        productRepository.save(product);
        return "redirect:/admin/newProduct";
    }

    @PostMapping("/admin/deleteProduct")
    public String deleteProduct(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }


    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> listProducts() {
        return productRepository.findAll();
    }


}
