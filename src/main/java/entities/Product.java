package entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @JsonProperty("articleNumber")
    private String articleNumber;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("color")
    private String color;
    @JsonProperty("size")
    private List<Size> sizes;
    @JsonProperty("price")
    private double price;

    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public Product deserialize(String productDetails) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(productDetails, Product.class);
    }
}
