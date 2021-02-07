package com.xlab.leedsbeerquest.entities;

import org.hibernate.annotations.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

    private @Id @GeneratedValue Long id;
    private String addressLine;
    private double lat;
    private double lng;
    private @OneToOne Venue venue;

    public Address() { }

    public Address(String addressLine, double lat, double lng) {
     this.setAddressLine(addressLine);
     this.setLat(lat);
     this.setLng(lng);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
