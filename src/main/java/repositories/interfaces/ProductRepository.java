package repositories.interfaces;

import entities.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();

    void addProduct(Product p);
}
