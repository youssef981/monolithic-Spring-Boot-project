package com.youssef.companyjob.job;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    void createJob(Job job);

    Job findJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);

    void deleteJob(Long id);
}
