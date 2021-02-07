package com.xlab.leedsbeerquest.exceptions;

public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException() {
        super("Could not find review");
    }
}
