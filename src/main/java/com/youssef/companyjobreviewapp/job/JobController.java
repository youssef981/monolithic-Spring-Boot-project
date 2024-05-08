package com.youssef.companyjob.job;

import com.youssef.companyjob.company.Company;
import com.youssef.companyjob.company.CompanyService;
import com.youssef.companyjob.job.imp.JobServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private JobServiceImp jobServiceImp;
    private CompanyService companyService;



    public JobController(JobServiceImp jobServiceImp,CompanyService companyService) {
        this.jobServiceImp = jobServiceImp;
        this.companyService = companyService;
    }

    @GetMapping(path="/jobs")
    public ResponseEntity<List<Job>> findAllJobs(){
        return ResponseEntity.ok(jobServiceImp.findAll());
    }

    @PostMapping(path="/jobs")
    public ResponseEntity<String> addJob(@RequestBody Job job){
        jobServiceImp.createJob(job);
        return ResponseEntity.ok("job was added successfully");
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobServiceImp.findJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/job/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) {
        boolean updated = jobServiceImp.updateJob(id, job);
        if (updated) {
            return new ResponseEntity<>("The job with ID " + id + " was updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job with ID " + id + " not found. Update failed.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Job job = jobServiceImp.findJobById(id);
        if (job != null) {
            jobServiceImp.deleteJob(id);
            return new ResponseEntity<>("Job with ID " + id + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
