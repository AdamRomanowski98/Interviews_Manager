package com.crud.interviewsWebService.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class CompanyDto {

    private Long id;

    private String name;

    private String localization;

    private double fromSalary;

    private double toSalary;

    private String typeOfContract;

    private Set<String> mustHaveTechnologies;

    private Set<String> niceToHaveTechnologies;

    private boolean answered;

    private String interviewDescription;

    private LocalDate dateOfInterview;

}
