package ru.geekbrains.spring.mvc.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.mvc.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Espresso", 25));
        products.add(new Product(2L, "Red", 15));
        products.add(new Product(3L, "Yellow", 25));
        products.add(new Product(4L, "Yellow", 22));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public List<Product> getById(Long id) {
        List<Product> productsById = new ArrayList<>();
        for (Product product: products) {
            if (product.getId().equals(id))
                productsById.add(product);
        }
        return Collections.unmodifiableList(productsById);
    }

    public void save(Product product) {
        products.add(product);
    }

    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

}
