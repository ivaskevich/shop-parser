package entities.size;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Size {
    String size();

    String serialize() throws JsonProcessingException;

    Size deserialize(String sizeDetails) throws JsonProcessingException;
}
