package com.reservationsystem.application;

import com.reservationsystem.domain.Review;
import com.reservationsystem.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


public class ReviewServiceTest {

   private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
       MockitoAnnotations.initMocks(this);
      reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview() {
        Review review = Review.builder()
                .name("JOKER")
                .score(3)
                .description("Mat-id-da")
                .build();

        reviewService.addReview(1004L, "JOKER", 3, "Mat-it-da");

       verify(reviewRepository).save(any());

    }


}