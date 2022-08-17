package com.crud.interviewsWebService.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class CompanyDto {

    private Long id;

    private String name;

    private String localization;

    private double fromSalary;

    private double toSalary;

    private String typeOfContract;

    private List<String> mustHaveTechnologies = new ArrayList<>();

    private List<String> niceToHaveTechnologies = new ArrayList<>();

    private boolean answered;

    private String interviewDescription;
}
