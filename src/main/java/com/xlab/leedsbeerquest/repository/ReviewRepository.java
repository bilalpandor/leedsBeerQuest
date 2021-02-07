package com.xlab.leedsbeerquest.repository;

import com.xlab.leedsbeerquest.entities.Review;
import com.xlab.leedsbeerquest.entities.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
