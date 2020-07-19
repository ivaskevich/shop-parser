package repositories.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Product;
import lombok.AllArgsConstructor;
import repositories.interfaces.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    @JsonProperty("products")
    private final List<Product> products;

    public ProductRepositoryImpl() {
        this.products = new ArrayList<>();
    }

    @Override
    public List<Product> findAllProducts() {
        return products;
    }

    @Override
    public void addProduct(Product p) {
        products.add(p);
    }

    @Override
    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
