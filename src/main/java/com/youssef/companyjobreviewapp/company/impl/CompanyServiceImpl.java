package com.youssef.companyjob.company.impl;


import com.youssef.companyjob.company.Company;
import com.youssef.companyjob.company.CompanyRepository;
import com.youssef.companyjob.company.CompanyService;
import com.youssef.companyjob.job.Job;
import com.youssef.companyjob.job.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean addCompany(Company company) {
        Company savedCompany = companyRepository.save(company);
        return true;
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }


    @Override
    public boolean deleteCompany(Long id) {
        Optional<Company> c = companyRepository.findById(id);
        if (c.isPresent()){
            Company company = c.get();
            List<Job> jobs = company.getJobs();
            for(Job j : jobs){
                jobRepository.deleteById(j.getId());
            }
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }



    public boolean update(Company comp,Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company companyUpdate = optionalCompany.get();
            companyUpdate.setDescription(comp.getDescription());
            companyUpdate.setName(comp.getName());
            companyUpdate.setJobs(comp.getJobs());
            companyRepository.save(companyUpdate);
            return true;
        }
        return false;
    }

    public void getAllJobsOfACompany(Company company){
        for(Job job:company.getJobs()){
            System.out.println(job.toString());
        }
    }

    public Company getCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }
}
