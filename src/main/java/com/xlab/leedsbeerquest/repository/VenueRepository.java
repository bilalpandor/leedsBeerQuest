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

    @Transactional
    @Modifying
    @Query(value = "UPDATE venue SET review_id = ?1 WHERE id = ?2", nativeQuery = true)
    void updateVenueReview(Long reviewId, Long venueId);

    @Transactional
    void deleteByName(String name);

}
