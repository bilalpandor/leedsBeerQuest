package com.xlab.leedsbeerquest.entities;

import lombok.*;
import org.hibernate.annotations.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    private @Id @GeneratedValue Long id;
    private String addressLine;
    private double lat;
    private double lng;
    private @OneToOne @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) Venue venue;

    public Address(String addressLine, double lat, double lng) {
     this.setAddressLine(addressLine);
     this.setLat(lat);
     this.setLng(lng);
    }
}
