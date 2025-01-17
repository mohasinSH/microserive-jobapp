package com.exbarkx.jobapplication.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public  ResponseEntity<String> addReviews(@PathVariable Long companyId, @RequestBody Review review){
      boolean isreviewSaved = reviewService.addReview(companyId,review);
      if (isreviewSaved){
          return new ResponseEntity<>("Review Added SucessFully",HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(companyId,reviewId),HttpStatus.OK);
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId, @RequestBody  Review review){
        boolean updated = reviewService.updateReview(companyId,reviewId,review);
        if (updated){
            return new ResponseEntity<>("review Updated Sucessfully",HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId, @PathVariable Long companyId){
        boolean deleted = reviewService.deleteReview(companyId,reviewId);
        if (deleted){
            return new ResponseEntity<>("review Deleted Sucessfully",HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
