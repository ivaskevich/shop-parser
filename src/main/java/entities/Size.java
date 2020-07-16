package entities;

import lombok.Getter;

@Getter
public enum Size {
    XS, S, M, L, XL, XXL, XXXL;

    String sizeValue;

    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue;
    }
}
