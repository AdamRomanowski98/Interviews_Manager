package com.crud.interviewsWebService.controller;

import com.crud.interviewsWebService.domain.Company;
import com.crud.interviewsWebService.domain.CompanyDto;
import com.crud.interviewsWebService.mapper.CompanyMapper;
import com.crud.interviewsWebService.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final DbService dbService;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyController(DbService dbService, CompanyMapper companyMapper) {
        this.dbService = dbService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getCompany(){
        List<Company> companies = dbService.getAllCompanies();
        return ResponseEntity.ok(companyMapper.mapToCompanyDtoList(companies));
    }


    @GetMapping(value = "name/{companyName}")
    public ResponseEntity<List<CompanyDto>> getCompaniesByName(@PathVariable String companyName){
        List<Company> companies = dbService.getCompaniesByName(companyName);
        return ResponseEntity.ok(companyMapper.mapToCompanyDtoList(companies));
    }

    @GetMapping(value = "city/{companyLocalization}")
    public ResponseEntity<List<CompanyDto>> getCompaniesByLocalization(@PathVariable String companyLocalization){
        List<Company> companies = dbService.getCompaniesByLocalization(companyLocalization);
        return ResponseEntity.ok(companyMapper.mapToCompanyDtoList(companies));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCompany(@RequestBody CompanyDto companyDto){
        Company company = companyMapper.mapToCompany(companyDto);
        dbService.saveCompany(company);
        return ResponseEntity.ok().build();
    }

}
