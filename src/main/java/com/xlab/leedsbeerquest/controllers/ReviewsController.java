package com.xlab.leedsbeerquest.controllers;

import com.xlab.leedsbeerquest.entities.Review;
import com.xlab.leedsbeerquest.exceptions.ReviewNotFoundException;
import com.xlab.leedsbeerquest.exceptions.VenueNotFoundException;
import com.xlab.leedsbeerquest.repository.ReviewRepository;
import com.xlab.leedsbeerquest.repository.VenueRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewsController {

    private final ReviewRepository reviewRepository;
    private final VenueRepository venueRepository;


    ReviewsController(ReviewRepository reviewRepository, VenueRepository venueRepository) {
        this.reviewRepository = reviewRepository;
        this.venueRepository = venueRepository;
    }

    @GetMapping("/reviews")
    List<Review> all() {
        return reviewRepository.findAll();
    }

    @GetMapping("/review/{id}")
    Review one(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException());
    }

    @PostMapping("/review")
    Review newReview(@RequestBody Review review, Long venueId) {
        reviewRepository.save(review);
        venueRepository.updateVenueReview(review.getId(), venueId);
        return review;
    }

    @PutMapping("/review/{id}")
    Review replaceReview(@RequestBody Review newReview, @PathVariable Long id) {

        return reviewRepository.findById(id)
                .map(Review -> {
                    Review.setCategory(newReview.getCategory());
                    Review.setUrl(newReview.getUrl());
                    Review.setDate(newReview.getDate());
                    Review.setExcerpt(newReview.getExcerpt());
                    Review.setBeerStars(newReview.getBeerStars());
                    Review.setAtmosphereStars(newReview.getAtmosphereStars());
                    Review.setAmenitiesStars(newReview.getAmenitiesStars());
                    Review.setValueStars(newReview.getValueStars());
                    return reviewRepository.save(Review);
                })
                .orElseGet(() -> {
                    newReview.setId(id);
                    return reviewRepository.save(newReview);
                });
    }

    @DeleteMapping("/review/{id}")
    void deleteReview(@PathVariable Long id) {
        reviewRepository.deleteById(id);
    }
}
