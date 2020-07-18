package services.impl;

import lombok.Getter;

@Getter
public class HttpGetCounter {
    private int count;

    public HttpGetCounter() {
        this.count = 0;
    }

    public void increment() {
        count++;
    }
}
