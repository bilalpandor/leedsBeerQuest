package com.xlab.leedsbeerquest.controllers;

import com.xlab.leedsbeerquest.entities.Review;
import com.xlab.leedsbeerquest.entities.Venue;
import com.xlab.leedsbeerquest.enums.ReviewCategory;
import com.xlab.leedsbeerquest.exceptions.VenueNotFoundException;
import com.xlab.leedsbeerquest.repository.VenueRepository;
import com.xlab.leedsbeerquest.requestModels.RatingRequest;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Stream.builder;

@RestController
public class VenuesController {

    private final VenueRepository repository;

    VenuesController(VenueRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/venues")
    List<Venue> all() {
        return repository.findAll();
    }

    @GetMapping("/venues/id/{id}")
    Venue venueById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new VenueNotFoundException());
    }

    @GetMapping("/venues/name/{name}")
    List<Venue> venueByName(@PathVariable String name) {
        Venue example = Venue
                .builder()
                .name(name)
                .build();
        return repository.findAll(Example.of(example));
    }


    @GetMapping("/venues/reviewCategory/{category}")
    List<Venue> venueByReviewCategory(@PathVariable ReviewCategory category) {
        Venue example = Venue
                .builder()
                .review(Review.builder()
                        .category(category.label)
                        .build())
                .build();
        return repository.findAll(Example.of(example));
    }

    @PostMapping("/venues/rating/{ratingRequest}")
    List<Venue> venueByBeerRating(@RequestBody RatingRequest ratingRequest) {
        switch(ratingRequest.getRatingType()){
            case BEER_RATING:
            default:
                Venue beerExample = Venue
                        .builder()
                        .review(Review.builder()
                                .beerStars(ratingRequest.getRating())
                                .build())
                        .build();
                return repository.findAll(Example.of(beerExample));
            case AMENITIES_RATING:
                Venue amenitiesExample = Venue
                        .builder()
                        .review(Review.builder()
                                .amenitiesStars(ratingRequest.getRating())
                                .build())
                        .build();
                return repository.findAll(Example.of(amenitiesExample));
            case ATMOSPHERE_RATING:
                Venue atmosphereExample = Venue
                        .builder()
                        .review(Review.builder()
                                .atmosphereStars(ratingRequest.getRating())
                                .build())
                        .build();
                return repository.findAll(Example.of(atmosphereExample));
            case VALUE_RATING:
                Venue valueExample = Venue
                        .builder()
                        .review(Review.builder()
                                .valueStars(ratingRequest.getRating())
                                .build())
                        .build();
                return repository.findAll(Example.of(valueExample));
        }
    }

    @PostMapping("/venues")
    Venue newVenue(@RequestBody Venue newVenue) {
        return repository.save(newVenue);
    }

    @PutMapping("/venues/id/{id}")
    Venue updateVenueById(@RequestBody Venue newVenue, @PathVariable Long id) {

        return repository.findById(id)
                .map(venue -> {
                    return repository.save(newVenue);
                })
                .orElseGet(() -> {
                    newVenue.setId(id);
                    return repository.save(newVenue);
                });
    }

    @PutMapping("/venues/name/{name}")
    Venue updateVenueByName(@RequestBody Venue newVenue, @PathVariable String name) {

        return repository.findByName(name)
                .map(venue -> {
                    return repository.save(newVenue);
                })
                .orElseGet(() -> {
                    return repository.save(newVenue);
                });
    }

    @DeleteMapping("/venues/id/{id}")
    void deleteByVenueId(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/venues/name/{name}")
    void deleteByVenueName(@PathVariable String name) {
        repository.deleteByName(name);
    }

}
