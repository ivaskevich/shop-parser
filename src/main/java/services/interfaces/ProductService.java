package services.interfaces;

import entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    public void addProduct(Product p);
}
