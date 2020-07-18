package entities.size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProportionsSize implements Size {
    private String width;
    private String length;

    @Override
    public String getSize() {
        return null;
    }
}
