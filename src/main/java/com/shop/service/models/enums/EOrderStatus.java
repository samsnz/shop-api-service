package com.shop.service.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum EOrderStatus {

    PENDING("PENDING"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED");

    public final String label;

    private static final Map<String, EOrderStatus> BY_LABEL = new HashMap<>();

    static {
        for (EOrderStatus e : values()) {
            BY_LABEL.put(e.label, e);
        }
    }

    private EOrderStatus(String label) {
        this.label = label;
    }

    public static EOrderStatus valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

}
