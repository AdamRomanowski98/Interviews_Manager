package com.crud.interviewsWebService.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "companies")
public class Company {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name= "Company name")
    private String name;

    @NotNull
    @Column(name= "Localization")
    private String localization;

    @Column(name= "Minimum salary")
    private Double fromSalary;

    @Column(name= "Maximum salary")
    private Double toSalary;

    @Column(name= "Type of contract")
    private String typeOfContract;

    @ElementCollection
    @NotNull
    @CollectionTable(name="Must have",
    joinColumns = @JoinColumn(name = "companies_id"))
    @Column(name="Mandatory skills")
    private Set<String> mustHaveTechnologies;

    @ElementCollection
    @CollectionTable(name="Nice to have",
    joinColumns = @JoinColumn(name="companies_id"))
    @Column(name="Nice to have skills")
    private Set<String> niceToHaveTechnologies;

    @NotNull
    @Column(name= "Did they answer?")
    private Boolean answered;

    @Column(name= "Interview description")
    private String interviewDescription;

    @NotNull
    @Column(name = "Date of interview")
    private LocalDate dateOfInterview;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", localization='" + localization + '\'' +
                ", fromSalary=" + fromSalary +
                ", toSalary=" + toSalary +
                ", typeOfContract='" + typeOfContract + '\'' +
                ", mustHaveTechnologies=" + mustHaveTechnologies +
                ", niceToHaveTechnologies=" + niceToHaveTechnologies +
                ", answered=" + answered +
                ", interviewDescription='" + interviewDescription + '\'' +
                ", dateOfInterview=" + dateOfInterview +
                '}';
    }
}
