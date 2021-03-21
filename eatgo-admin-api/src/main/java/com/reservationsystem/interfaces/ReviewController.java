package com.reservationsystem.interfaces;

import com.reservationsystem.application.ReviewService;
import com.reservationsystem.domain.Review;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(@PathVariable("restaurantId") Long restaurantId,
                                    @Valid @RequestBody Review resource) throws URISyntaxException {
        Review review =reviewService.addReview(restaurantId,resource);

        String url = "/restaurants/"+ restaurantId +"/reviews/" + review.getId();
        return ResponseEntity.created(new URI(url))
        .body("{}");
    }

    @GetMapping("/reviews")
    public List<Review> list(){

        List<Review> reviews =reviewService.getReviews();
        return reviews;
    }

}
