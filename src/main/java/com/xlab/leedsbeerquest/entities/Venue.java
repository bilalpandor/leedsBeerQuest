package com.xlab.leedsbeerquest.entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Venue {

    private @Id @GeneratedValue Long id;
    private String name;
    private @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "address_id", referencedColumnName = "id") Address address;
    private String phoneNumber;
    private String twitterHandle;
    private @OneToOne(cascade = CascadeType.ALL)  @JoinColumn(name = "review_id", referencedColumnName = "id") Review review;
    private ArrayList<String> tags;
    private String venueImage;

    public Venue() {}

    public Venue (String name, Address address, String phoneNumber, String twitterHandle, Review review, ArrayList<String> tags, String venueImage) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.twitterHandle = twitterHandle;
        this.review = review;
        this.tags = tags;
        this.venueImage = venueImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getVenueImage() {
        return venueImage;
    }

    public void setVenueImage(String venueImage) {
        this.venueImage = venueImage;
    }
}
