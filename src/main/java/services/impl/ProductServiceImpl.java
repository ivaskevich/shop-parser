package services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import entities.Product;
import lombok.AllArgsConstructor;
import repositories.interfaces.ProductRepository;
import services.interfaces.ProductService;

import java.util.List;

import static utils.FileUtils.selectFolderAndSave;

@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public void addProduct(Product p) {
        if (p != null) {
            productRepository.addProduct(p);
        }
    }

    @Override
    public String serialize() throws JsonProcessingException {
        return productRepository.serialize();
    }

    @Override
    public void writeJsonDataToFile() {
        try {
            selectFolderAndSave(serialize());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
