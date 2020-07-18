package services.impl;

import entities.Product;
import lombok.AllArgsConstructor;
import repositories.interfaces.ProductRepository;
import services.interfaces.ProductService;

import java.util.List;

@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
    @Override
    public void addProduct(Product p) {
        if (p != null) {
            productRepository.addProduct(p);
            System.out.println("product was added");
        }
    }
}
