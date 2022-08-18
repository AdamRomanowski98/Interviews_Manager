package com.crud.interviewsWebService.service;

import com.crud.interviewsWebService.domain.Company;
import com.crud.interviewsWebService.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public List<Company> getCompaniesByName(String name){
        return companyRepository.findByName(name);
    }

    public List<Company> getCompaniesByLocalization(String localization){
        return companyRepository.findByLocalization(localization);
    }

    public Company saveCompany(final Company company){
        return companyRepository.save(company);
    }

    public List<Company> getCompaniesByAnswered(boolean answered){
        return companyRepository.findCompaniesByAnswered(answered);
    }

    public void deleteCompanyById(final Long id){
        companyRepository.deleteById(id);
    }


}
