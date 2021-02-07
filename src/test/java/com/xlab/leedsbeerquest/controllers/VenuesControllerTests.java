package com.xlab.leedsbeerquest.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.JsonPath;
import com.xlab.leedsbeerquest.entities.Venue;
import com.xlab.leedsbeerquest.enums.ReviewCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.*;


import java.lang.reflect.Type;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VenuesControllerTests {

    @Autowired
    private MockMvc mockMvc;

    Gson gson = new Gson();

    @Test
    public void shouldReturnAll242Venues() throws Exception {
        int expectedVenues = 242;

        MvcResult mvcResult = this.mockMvc.perform(get("/venues"))
                .andExpect(status().isOk())
                .andReturn();

        Type collectionType = new TypeToken<List<Venue>>(){}.getType();
        List<Venue> venues = gson.fromJson(mvcResult.getResponse().getContentAsString(), collectionType);

        assertThat(venues.size()).isEqualTo(expectedVenues);
    }

    @Test
    public void shouldReturnParticularVenueById() throws Exception {
        String expectedVenueName = "...escobar";
        int venueId = 1;

        MvcResult mvcResult = this.mockMvc.perform(get("/venues/id/" + venueId))
                .andExpect(status().isOk())
                .andReturn();

        Type collectionType = new TypeToken<Venue>(){}.getType();
        Venue venues = gson.fromJson(mvcResult.getResponse().getContentAsString(), collectionType);

        assertThat(venues.getName()).isEqualTo(expectedVenueName);
    }

    @Test
    public void shouldNotReturnVenueDueToInvalidId() throws Exception {
        int venueId = 999;
        this.mockMvc.perform(get("/venues/id/" + venueId))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Could not find venue"));
    }

    @Test
    public void shouldReturnVenueByName() throws Exception {
        String expectedVenueName = "1871 Bar & Lounge";

        MvcResult mvcResult = this.mockMvc.perform(get("/venues/name/" + expectedVenueName))
                .andExpect(status().isOk())
                .andReturn();

        Type collectionType = new TypeToken<Venue>(){}.getType();
        Venue venues = gson.fromJson(mvcResult.getResponse().getContentAsString(), collectionType);

        assertThat(venues.getName()).isEqualTo(expectedVenueName);
    }

    @Test
    public void shouldNotReturnVenueDueToInvalidName() throws Exception {
        String expectedVenueName = "Fake Pub 9000";

        this.mockMvc.perform(get("/venues/name/" + expectedVenueName))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Could not find venue"));
    }

    @Test
    public void shouldReturnVenuesWithSpecificReviewCategory() throws Exception {
        ReviewCategory category = ReviewCategory.PUB_REVIEWS;

        MvcResult mvcResult = this.mockMvc.perform(get("/venues/reviewCategory/" + category))
                .andExpect(status().isOk())
                .andReturn();

        Type collectionType = new TypeToken<List<Venue>>(){}.getType();
        List<Venue> venues = gson.fromJson(mvcResult.getResponse().getContentAsString(), collectionType);

        venues.stream().map( venue -> assertThat(venue.getReview().getCategory()).isEqualTo(category));
    }

    @Test
    public void shouldNotReturnVenuesWithUnexpectedReviewCategory() throws Exception {
        ReviewCategory category = ReviewCategory.PUB_REVIEWS;
        ReviewCategory unexpectedCategory = ReviewCategory.OTHER_REVIEWS;

        MvcResult mvcResult = this.mockMvc.perform(get("/venues/reviewCategory/" + category))
                .andExpect(status().isOk())
                .andReturn();

        Type collectionType = new TypeToken<List<Venue>>(){}.getType();
        List<Venue> venues = gson.fromJson(mvcResult.getResponse().getContentAsString(), collectionType);

        venues.stream().map( venue -> assertThat(venue.getReview().getCategory()).isNotEqualTo(unexpectedCategory));
    }

    @Test
    public void shouldDeleteVenueById() throws Exception {
        int venueId = 1;

        this.mockMvc.perform(delete("/venues/id/" + venueId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteVenueByName() throws Exception {
        String venueName = "...escobar";

        this.mockMvc.perform(delete("/venues/name/" + venueName))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailToDeleteVenueDueToInvalidName() throws Exception {
        String venueName = "Fake Pub";

        this.mockMvc.perform(delete("/venues/name/" + venueName))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldUpdateVenueByName() throws Exception {
        String venueName = "360 Champagne & Cocktails";
        String newTwitterhandle = "NewTwitter";

        MvcResult mvcResult = this.mockMvc.perform(get("/venues/name/" + venueName))
                .andReturn();
        Type collectionType = new TypeToken<Venue>(){}.getType();
        Venue venue = gson.fromJson(mvcResult.getResponse().getContentAsString(), collectionType);
        venue.setTwitterHandle(newTwitterhandle);

        this.mockMvc.perform(put("/venues/name/" + venueName).contentType(MediaType.APPLICATION_JSON).content(gson.toJson(venue)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailToUpdateVenueDueToInvalidName() throws Exception {
//        String venueName = "Fake Pub";
//
//        this.mockMvc.perform(delete("/venues/name/" + venueName))
//                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldUpdateVenueById() throws Exception {
//        String venueName = "Fake Pub";
//
//        this.mockMvc.perform(delete("/venues/name/" + venueName))
//                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldFailToUpdateVenueDueToId() throws Exception {
//        String venueName = "Fake Pub";
//
//        this.mockMvc.perform(delete("/venues/name/" + venueName))
//                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldUploadNewVenue() throws Exception {
//        String venueName = "Fake Pub";
//
//        this.mockMvc.perform(delete("/venues/name/" + venueName))
//                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldFailToUploadNewVenueDuetoInvalidJson() throws Exception {
//        String venueName = "Fake Pub";
//
//        this.mockMvc.perform(delete("/venues/name/" + venueName))
//                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnListOfVenuesMatchingRatingCriteria() throws Exception {
//        String venueName = "Fake Pub";
//
//        this.mockMvc.perform(delete("/venues/name/" + venueName))
//                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldNotReturnListOfVenuesDueToInvalidRatingCriteria() throws Exception {
//        String venueName = "Fake Pub";
//
//        this.mockMvc.perform(delete("/venues/name/" + venueName))
//                .andExpect(status().is4xxClientError());
    }


}
