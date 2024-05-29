package com.grupob.resolvo.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Position {
    TECNICO,
    ADMINISTRADOR;

    private static final Map<String, Position> NAME_TO_ENUM = new HashMap<>();

    static {
        for (Position position : Position.values()) {
            NAME_TO_ENUM.put(position.name().toLowerCase(), position);
        }
    }

    public static Position fromString(String name) {
        return NAME_TO_ENUM.get(name.toLowerCase());
    }
}
