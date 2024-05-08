package com.youssef.companyjob.job;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.youssef.companyjob.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_table")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minsalary;
    private String maxsalary;
    private String location;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", minsalary='" + minsalary + '\'' +
                ", maxsalary='" + maxsalary + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
