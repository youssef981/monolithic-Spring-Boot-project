package com.youssef.companyjob.job;

import jakarta.transaction.Transactional;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface JobRepository extends JpaRepository<Job,Long > {

    @Modifying
    @Transactional
    @Query("UPDATE Job j SET j.title = :#{#newJob.title}, j.description = :#{#newJob.description}, j.minsalary = :#{#newJob.minsalary}, j.maxsalary = :#{#newJob.maxsalary}, j.location = :#{#newJob.location} WHERE j.id = :id")
    int updateJob(@Param("newJob") Job newJob,@Param("id") Long id);
}
