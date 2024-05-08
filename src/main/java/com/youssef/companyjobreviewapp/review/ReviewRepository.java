package com.youssef.companyjob.review;



import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.company.id = :companyId")
    List<Review> getReviewsByCompanyId(@Param("companyId") Long companyId);

    @Modifying
    @Query("DELETE FROM Review review WHERE review.id = :revId AND review.company.id = :idC")
    int deleteReviewByIdC(@Param("revId") Long revId, @Param("idC") Long idC);


}
