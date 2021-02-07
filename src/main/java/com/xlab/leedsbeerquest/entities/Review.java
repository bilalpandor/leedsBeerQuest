package com.xlab.leedsbeerquest.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

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

    @OneToOne(mappedBy = "review") private Venue venue;

    public Review() {}

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public Double getBeerStars() {
        return beerStars;
    }

    public void setBeerStars(Double beerStars) {
        this.beerStars = beerStars;
    }

    public Double getAtmosphereStars() {
        return atmosphereStars;
    }

    public void setAtmosphereStars(Double atmosphereStars) {
        this.atmosphereStars = atmosphereStars;
    }

    public Double getAmenitiesStars() {
        return amenitiesStars;
    }

    public void setAmenitiesStars(Double amenitiesStars) {
        this.amenitiesStars = amenitiesStars;
    }

    public Double getValueStars() {
        return valueStars;
    }

    public void setValueStars(Double valueStars) {
        this.valueStars = valueStars;
    }
}
