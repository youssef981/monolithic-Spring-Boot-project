package com.youssef.companyjob.company;

import java.util.List;


public interface CompanyService {

    boolean addCompany(Company company);
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);
    boolean deleteCompany(Long id);

    boolean update(Company company,Long id);
}
