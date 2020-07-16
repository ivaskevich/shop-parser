package repositories.impl;

import entities.Product;
import lombok.AllArgsConstructor;
import repositories.interfaces.ProductRepository;

import java.util.List;

@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> products;

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public void addProduct(Product p) {
        products.add(p);
    }
}
