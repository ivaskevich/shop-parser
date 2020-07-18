package entities.size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LabelInchSize implements Size {
    private String label;
    private String inch;

    @Override
    public String getSize() {
        return null;
    }
}
