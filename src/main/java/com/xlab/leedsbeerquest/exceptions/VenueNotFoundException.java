package com.xlab.leedsbeerquest.exceptions;

public class VenueNotFoundException extends RuntimeException {

    public VenueNotFoundException() {
        super("Could not find venue");
    }
}
