package services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();

    void addProduct(Product p);

    String serialize() throws JsonProcessingException;

    void writeJsonDataToFile();
}
