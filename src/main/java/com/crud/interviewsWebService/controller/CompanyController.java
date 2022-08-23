package com.crud.interviewsWebService.controller;

import com.crud.interviewsWebService.domain.Company;
import com.crud.interviewsWebService.domain.CompanyDto;
import com.crud.interviewsWebService.mapper.CompanyMapper;
import com.crud.interviewsWebService.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @GetMapping(value = "answered/{companyAnswered}")
    public ResponseEntity<List<CompanyDto>> getCompaniesByAnswered(@PathVariable boolean companyAnswered){
        List<Company> companies = dbService.getCompaniesByAnswered(companyAnswered);
        return ResponseEntity.ok(companyMapper.mapToCompanyDtoList(companies));
    }

    @PutMapping
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto companyDto){
        Company company = companyMapper.mapToCompany(companyDto);
        Company newCompany = dbService.saveCompany(company);
        return ResponseEntity.ok(companyMapper.mapToCompanyDto(newCompany));
    }

    @GetMapping("filter")
    public ResponseEntity<List<CompanyDto>> getCompaniesWithFilters(@RequestParam(value = "name", required= false) String name, @RequestParam(value = "localization", required= false) String localization,
                                                                    @RequestParam(value = "fromSalary", required= false) double fromSalary, @RequestParam(value = "toSalary", required= false) Double toSalary,
                                                                    @RequestParam(value = "typeOfContract", required= false) String typeOfContract, @RequestParam(value = "mustHaveTechnologies", required= false) Set<String> mustHaveTechnologies,
                                                                    @RequestParam(value = "niceToHaveTechnologies", required= false) Set<String> niceToHaveTechnologies, @RequestParam(value = "answered", required= false) Boolean answered,
                                                                    @RequestParam(value = "interviewDescription", required= false) String interviewDescription, @RequestParam(value = "dateOfInterview", required= false)LocalDate dateOfInterview){

        Specification<Company> specification = dbService.getSpec(name, localization, fromSalary, toSalary, typeOfContract, mustHaveTechnologies, niceToHaveTechnologies, answered, interviewDescription, dateOfInterview);
        return ResponseEntity.ok(companyMapper.mapToCompanyDtoList(dbService.getFiltered(specification)));
    }


    @DeleteMapping(value= "{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId){
        dbService.deleteCompanyById(companyId);
        return ResponseEntity.ok().build();
    }

}
