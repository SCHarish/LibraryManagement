package com.harish.library.model;

import java.util.HashMap;
import java.util.Map;

public enum Tag {
    LITERATURE(0),
    NONFICTION(1),
    ACTION(2),
    THRILLER(3),
    TECHNOLOGY(4),
    DRAMA(5),
    POETRY(6),
    MEDIA(7),
    OTHERS(8);

    private int value;
    private static Map map = new HashMap<>();

    private Tag(int value) {
        this.value = value;
    }

    static {
        for (Tag category : Tag.values()) {
            map.put(category.value, category);
        }
    }

    public static Tag valueOf(int category) {
        return (Tag) map.get(category);
    }

    public int getValue() {
        return value;
    }
}