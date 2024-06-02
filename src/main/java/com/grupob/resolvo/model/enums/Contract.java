package com.grupob.resolvo.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Contract {
    NOTIENE,
    LIMITADO,
    ILIMITADO;

    private static final Map<String, Contract> NAME_TO_ENUM = new HashMap<>();

    static {
        for (Contract contract : Contract.values()) {
            NAME_TO_ENUM.put(contract.name().toLowerCase(), contract);
        }
    }

    public static Contract fromString(String name) {
        return NAME_TO_ENUM.get(name.toLowerCase());
    }
}
