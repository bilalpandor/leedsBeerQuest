package com.xlab.leedsbeerquest.entities;

import jdk.jfr.DataAmount;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    private @Id @GeneratedValue Long id;
    private String category;
    private String url;
    private Date date;
    private String excerpt;
    private Double beerStars;
    private Double atmosphereStars;
    private Double amenitiesStars;
    private Double valueStars;
    private @OneToOne(mappedBy = "review") @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) Venue venue;

    public Review(String category, String url, Date date, String excerpt, Double beerStars, Double atmosphereStars, Double amenitiesStars, Double valueStars) {
        this.category = category;
        this.url = url;
        this.date = date;
        this.excerpt = excerpt;
        this.beerStars = beerStars;
        this.atmosphereStars = atmosphereStars;
        this.amenitiesStars = amenitiesStars;
        this.valueStars = valueStars;
    }
}
