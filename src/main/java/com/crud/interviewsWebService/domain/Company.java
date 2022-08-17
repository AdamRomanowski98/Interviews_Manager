package com.crud.interviewsWebService.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String name;

    @NotNull
    private String localization;

    private double fromSalary;

    private double toSalary;

    private String typeOfContract;

    @ElementCollection
    @NotNull
    @CollectionTable(name="Must_Have",
    joinColumns = @JoinColumn(name = "companies_id"))
    @Column(name="Mandatory_Skills")
    private List<String> mustHaveTechnologies = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="Nice_To_Have",
    joinColumns = @JoinColumn(name="companies_id"))
    @Column(name="Nice_To_Have_Skills")
    private List<String> niceToHaveTechnologies = new ArrayList<>();

    @NotNull
    private boolean answered;

    private String interviewDescription;

}
