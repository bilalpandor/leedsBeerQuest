package com.xlab.leedsbeerquest.enums;

public enum ReviewCategory {
    BAR_REVIEWS("Bar reviews"),
    PUB_REVIEWS("Pub reviews"),
    OTHER_REVIEWS("Other reviews"),
    CLOSED_VENUES("Closed venues");

    public final String label;

    private ReviewCategory(String label) {
        this.label = label;
    }
}
