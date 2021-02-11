package com.xlab.leedsbeerquest.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Venue (String name, Address address, String phoneNumber, String twitterHandle, Review review, ArrayList<String> tags, String venueImage) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.twitterHandle = twitterHandle;
        this.review = review;
        this.tags = tags;
        this.venueImage = venueImage;
    }
}
