package com.youssef.companyjob.review;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    public List<Review> getReviewsByCompany(Long compId);
    public boolean addReviewToCompany(Long compId,Review review);
    public Review getReviewByCompany(Long compId,Long revId);
    public boolean updateReviewByCompany(Long compId,Long revId,Review review);
    public boolean deleteReviewByCompany(Long compId,Long revId);
}
