package com.xlab.leedsbeerquest.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xlab.leedsbeerquest.entities.Review;
import com.xlab.leedsbeerquest.entities.Venue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.lang.reflect.Type;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    Gson gson = new Gson();

    @Test
    public void shouldReturnAll242Reviews() throws Exception {
        int expectedReviews = 242;

        MvcResult mvcResult = this.mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andReturn();

        Type collectionType = new TypeToken<List<Review>>(){}.getType();
        List<Review> reviews = gson.fromJson(mvcResult.getResponse().getContentAsString(), collectionType);

        assertThat(reviews.size()).isEqualTo(expectedReviews);
    }

}
