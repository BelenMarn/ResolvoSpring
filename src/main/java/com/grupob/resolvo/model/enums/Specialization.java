package com.grupob.resolvo.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Specialization {
    MOVIL,
    PC,
    PORTATILES,
    ELECTRODOMESTICOS,
    OTRO;

    private static final Map<String, Specialization> NAME_TO_ENUM = new HashMap<>();

    static {
        for (Specialization specialization : Specialization.values()) {
            NAME_TO_ENUM.put(specialization.name().toLowerCase(), specialization);
        }
    }

    public static Specialization fromString(String name) {
        return NAME_TO_ENUM.get(name.toLowerCase());
    }
}
