package com.xlab.leedsbeerquest.repository;

import com.xlab.leedsbeerquest.entities.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue, Long> {

    Optional<Venue> findByName(String name);


    @Query(value = "SELECT * FROM venue " +
            "INNER JOIN review ON review.id = venue.review_id " +
            "INNER JOIN address ON address.id = venue.address_id " +
            "WHERE review.category = ?1", nativeQuery = true)
    Optional<List<Venue>> findByReviewCategory(String category);

    @Query(value = "SELECT * FROM venue " +
                    "INNER JOIN review ON review.id = venue.review_id " +
                    "INNER JOIN address ON address.id = venue.address_id " +
                    "WHERE review.beerstars = ?1", nativeQuery = true)
    Optional<List<Venue>> findByBeerStars(double value);

    @Query(value = "SELECT * FROM venue " +
            "INNER JOIN review ON review.id = venue.review_id " +
            "INNER JOIN address ON address.id = venue.address_id " +
            "WHERE review.atmosphereStars = ?1", nativeQuery = true)
    Optional<List<Venue>> findByAtmosphereStars(double value);

    @Query(value = "SELECT * FROM venue " +
            "INNER JOIN review ON review.id = venue.review_id " +
            "INNER JOIN address ON address.id = venue.address_id " +
            "WHERE review.amenitiesStars = ?1", nativeQuery = true)
    Optional<List<Venue>> findByAmenitiesRating(double value);

    @Query(value = "SELECT * FROM venue " +
            "INNER JOIN review ON review.id = venue.review_id " +
            "INNER JOIN address ON address.id = venue.address_id " +
            "WHERE review.valueStars = ?1", nativeQuery = true)
    Optional<List<Venue>> findByValueRating(double value);

    @Transactional
    @Modifying
    @Query(value = "UPDATE venue SET review_id = ?1 WHERE id = ?2", nativeQuery = true)
    void updateVenueReview(Long reviewId, Long venueId);

    @Transactional
    void deleteByName(String name);

}
