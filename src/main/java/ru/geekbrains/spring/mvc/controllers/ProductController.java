package ru.geekbrains.spring.mvc.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.geekbrains.spring.mvc.models.Product;
import ru.geekbrains.spring.mvc.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProducts(@RequestParam(name = "find_id", defaultValue = "0") Long find_id, Model model) {
        if (find_id > 0)
            model.addAttribute("products", productService.getById(find_id));
        else
            model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @PostMapping("/add")
    public String getNewProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products/all";
    }

    @PostMapping("/find")
    public String findProduct(@RequestParam(name = "find_id", defaultValue = "0") String find_id) {
        //productService.getProductById(product);
        return "redirect:/products/all?find_id=" + find_id;
    }

}
