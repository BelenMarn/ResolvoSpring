package com.grupob.resolvo.model;

import java.util.HashMap;
import java.util.Map;

public enum Especializacion {
    MOVIL,
    PC,
    PORTAILES,
    ELECTRODOMESTICOS,
    OTRO;

    private static final Map<String, Especializacion> NAME_TO_ENUM = new HashMap<>();

    static {
        for (Especializacion especializacion : Especializacion.values()) {
            NAME_TO_ENUM.put(especializacion.name().toLowerCase(), especializacion);
        }
    }

    public static Especializacion fromString(String name) {
        return NAME_TO_ENUM.get(name.toLowerCase());
    }
}
