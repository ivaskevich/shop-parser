package services.impl;

import entities.Product;
import lombok.AllArgsConstructor;
import repositories.interfaces.ProductRepository;

import java.util.List;

@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void addProduct(Product p) {
        if (p != null) productRepository.addProduct(p);
    }
}
