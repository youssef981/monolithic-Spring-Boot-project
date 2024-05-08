package com.youssef.companyjob.review;


import com.youssef.companyjob.company.Company;
import com.youssef.companyjob.review.impl.ReviewServiceImpl;
import jakarta.persistence.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.youssef.companyjob.company.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class ReviewController {

    private ReviewServiceImpl reviewService;
    private CompanyService companyService;

    public ReviewController(ReviewServiceImpl reviewServiceImpl, CompanyService companyService) {
        this.reviewService = reviewServiceImpl;
        this.companyService = companyService;
    }


    @PostMapping("/{id}/reviews")
    public ResponseEntity<String> addReviewToCompany(@PathVariable Long id, @RequestBody Review review) {
        if(reviewService.addReviewToCompany(id, review)){
            return ResponseEntity.status(HttpStatus.OK).body("the review was added seccefully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("adding review was failed");
    }

    @GetMapping("/{idC}/reviews")
    public ResponseEntity<?> getAllreviewsBycompany(@PathVariable Long idC){
        if(reviewService.getReviewsByCompany(idC).isEmpty()){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not Found !");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewsByCompany(idC));

    }

    @GetMapping("/{idC}/reviews/{idR}")
    public ResponseEntity<?> getAllReviewsByCompany(@PathVariable("idC") Long idC, @PathVariable("idR") Long idR) {
        Review review = reviewService.getReviewByCompany(idC, idR);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }


    @PutMapping("/{idC}/reviews/{idR}")
    public ResponseEntity<?> updateCompany(@PathVariable("idC") Long idC,@PathVariable("idR") Long idR,@RequestBody Review review){
        if(reviewService.updateReviewByCompany(idC,idR,review)){
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.updateReviewByCompany(idC,idR,review));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("failed to delete");
    }

    @DeleteMapping("/{idC}/reviews/{idR}")
    public ResponseEntity<?> deleteReviewOfComp(@PathVariable("idC") Long idC,@PathVariable("idR") Long idR){
        if(reviewService.deleteReviewByCompany(idC,idR)){
            return ResponseEntity.status(HttpStatus.OK).body("Succefully deleted");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete");
    }
}
