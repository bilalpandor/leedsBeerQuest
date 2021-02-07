package com.xlab.leedsbeerquest;

import static org.assertj.core.api.Assertions.assertThat;

import com.xlab.leedsbeerquest.controllers.ReviewsController;
import com.xlab.leedsbeerquest.controllers.VenuesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LeedsBeerQuestApiApplicationTests {
	@Autowired
	ReviewsController reviewsController;

	@Autowired
	VenuesController venuesController;

	@Test
	void contextLoads() throws Exception {
		assertThat(reviewsController).isNotNull();
		assertThat(venuesController).isNotNull();
	}

}
