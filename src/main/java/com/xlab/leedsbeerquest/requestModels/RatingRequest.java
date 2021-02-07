package com.xlab.leedsbeerquest.requestModels;

import com.xlab.leedsbeerquest.enums.RatingType;

public class RatingRequest {

    private RatingType ratingType;
    private double rating;

    public RatingRequest(RatingType ratingType, double rating) {
        this.setRatingType(ratingType);
        this.setRating(rating);
    }

    public RatingType getRatingType() {
        return ratingType;
    }

    public void setRatingType(RatingType ratingType) {
        this.ratingType = ratingType;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
