package com.youssef.companyjob.job.imp;

import com.youssef.companyjob.job.Job;
import com.youssef.companyjob.job.JobRepository;
import com.youssef.companyjob.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImp implements JobService {
     JobRepository jobrepo ;

    public JobServiceImp(JobRepository jobrepo) {
        this.jobrepo = jobrepo;
    }

    @Override
    public List<Job> findAll() {
        return jobrepo.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobrepo.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        return jobrepo.findById(id).orElse(null);
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        return jobrepo.updateJob(updatedJob,id)>0 ? true : false;
    }

    @Override
    public void deleteJob(Long id) {
        jobrepo.deleteById(id);
    }

}
