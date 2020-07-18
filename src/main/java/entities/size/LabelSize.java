package entities.size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LabelSize implements Size {
    private String label;

    @Override
    public String getSize() {
        return null;
    }
}
