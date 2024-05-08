package com.youssef.companyjob.review.impl;

import com.youssef.companyjob.company.Company;
import com.youssef.companyjob.company.CompanyRepository;
import com.youssef.companyjob.review.Review;
import com.youssef.companyjob.review.ReviewRepository;
import com.youssef.companyjob.review.ReviewService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyRepository companyRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }



    @Override
    public List<Review> getReviewsByCompany(Long compId) {
        return reviewRepository.getReviewsByCompanyId(compId);
    }

    @Override
    public boolean addReviewToCompany(Long compId,Review review) {
        Optional<Company> companyOptional = companyRepository.findById(compId);
        review.setCompany(companyOptional.get());
        reviewRepository.save(review);
        return true;
    }

    @Override
    public Review getReviewByCompany(Long compId, Long revId) {
        List<Review> reviews = this.getReviewsByCompany(compId);
        for (Review r:reviews){
            if(r.getId()==revId){
                return r;
            }
        }
        return null;
    }

    @Override
    public boolean updateReviewByCompany(Long compId, Long revId,Review newReview) {
        Review review = this.getReviewByCompany(compId,revId);
        review.setCompany(newReview.getCompany());
        review.setDescription(newReview.getDescription());
        review.setTitle(newReview.getTitle());
        review.setRating(newReview.getRating());
        this.addReviewToCompany(compId,review);
        return true;
    }

    @Override
    public boolean deleteReviewByCompany(Long compId, Long revId) {
        if (reviewRepository.deleteReviewByIdC(revId,compId) != 0){
            return true;
        }
        return false;
    }
}
