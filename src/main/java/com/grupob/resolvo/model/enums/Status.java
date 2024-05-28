package com.grupob.resolvo.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    PENDIENTE,
    ENCURSO,
    CERRADA,
    RETENIDA;

    private static final Map<String, Status> NAME_TO_ENUM = new HashMap<>();

    static {
        for (Status status : Status.values()) {
            NAME_TO_ENUM.put(status.name().toLowerCase(), status);
        }
    }

    public static Status fromString(String name) {
        return NAME_TO_ENUM.get(name.toLowerCase());
    }

}