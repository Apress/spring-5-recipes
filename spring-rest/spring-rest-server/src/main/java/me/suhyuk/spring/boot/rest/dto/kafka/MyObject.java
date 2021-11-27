package me.suhyuk.spring.boot.rest.dto.kafka;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(value = AccessLevel.PROTECTED)
public class MyObject {
    private String name;
    private int value;

    @Builder
    public MyObject(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
