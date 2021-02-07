package com.xlab.leedsbeerquest.controllers;

import com.xlab.leedsbeerquest.entities.Venue;
import com.xlab.leedsbeerquest.enums.ReviewCategory;
import com.xlab.leedsbeerquest.exceptions.VenueNotFoundException;
import com.xlab.leedsbeerquest.repository.VenueRepository;
import com.xlab.leedsbeerquest.requestModels.RatingRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    Venue venueByName(@PathVariable String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new VenueNotFoundException());
    }


    @GetMapping("/venues/reviewCategory/{category}")
    List<Venue> venueByReviewCategory(@PathVariable ReviewCategory category) {
        return repository.findByReviewCategory(category.label)
                .orElseThrow(() -> new VenueNotFoundException());
    }

    @PostMapping("/venues/rating/{ratingRequest}")
    List<Venue> venueByBeerRating(@RequestBody RatingRequest ratingRequest) {
        switch(ratingRequest.getRatingType()){
            case BEER_RATING:
            default:
                return repository.findByBeerStars(ratingRequest.getRating())
                        .orElseThrow(() -> new VenueNotFoundException());
            case AMENITIES_RATING:
                return repository.findByAmenitiesRating(ratingRequest.getRating())
                        .orElseThrow(() -> new VenueNotFoundException());
            case ATMOSPHERE_RATING:
                return repository.findByAtmosphereStars(ratingRequest.getRating())
                        .orElseThrow(() -> new VenueNotFoundException());
            case VALUE_RATING:
                return repository.findByValueRating(ratingRequest.getRating())
                        .orElseThrow(() -> new VenueNotFoundException());
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
                    return repository.save(venue);
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
                    return repository.save(venue);
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
