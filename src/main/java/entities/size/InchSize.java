package entities.size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InchSize implements Size {
    private String inch;

    @Override
    public String getSize() {
        return null;
    }
}
