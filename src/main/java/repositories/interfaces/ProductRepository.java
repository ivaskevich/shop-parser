package repositories.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import entities.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllProducts();

    void addProduct(Product p);

    String serialize() throws JsonProcessingException;
}
