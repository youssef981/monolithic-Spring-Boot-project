package com.youssef.companyjob.company;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @PostMapping()
    public ResponseEntity<String> addCompany(@RequestBody String copanyjSON) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Company company = objectMapper.readValue(copanyjSON, Company.class);
            boolean isCompanyAdded = companyService.addCompany(company);
            if (isCompanyAdded) {
                return ResponseEntity.ok("Company was added successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the company!");
    }

    @GetMapping()
    public List<Company> showAllCompanies(){
        return companyService.getAllCompanies();
    }


    @GetMapping("/company/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }


    @DeleteMapping("/company/{id}")
    public ResponseEntity<String> dropCompany(@PathVariable Long id){
        if (companyService.deleteCompany(id))
            return ResponseEntity.ok("Company was deleted successfully!");
        return ResponseEntity.ok("Company deleting company is Fialed!");
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
        boolean isUpdated = companyService.update(company,id);
        if (isUpdated) return ResponseEntity.ok("Company updated seccefully");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update the company");
    }
}
