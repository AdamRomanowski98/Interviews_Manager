package com.crud.interviewsWebService.mapper;

import com.crud.interviewsWebService.domain.Company;
import com.crud.interviewsWebService.domain.CompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyMapper {

    public Company mapToCompany(final CompanyDto companyDto){
        return new Company(
                companyDto.getId(),
                companyDto.getName(),
                companyDto.getLocalization(),
                companyDto.getFromSalary(),
                companyDto.getToSalary(),
                companyDto.getTypeOfContract(),
                companyDto.getMustHaveTechnologies(),
                companyDto.getNiceToHaveTechnologies(),
                companyDto.isAnswered(),
                companyDto.getInterviewDescription()
        );
    }

    public CompanyDto mapToCompanyDto(final Company company){
        return  new CompanyDto(
                company.getId(),
                company.getName(),
                company.getLocalization(),
                company.getFromSalary(),
                company.getToSalary(),
                company.getTypeOfContract(),
                company.getMustHaveTechnologies(),
                company.getNiceToHaveTechnologies(),
                company.isAnswered(),
                company.getInterviewDescription()
        );
    }

    public List<CompanyDto> mapToCompanyDtoList(final List<Company> companies){
        return companies.stream()
                .map(this::mapToCompanyDto)
                .collect(Collectors.toList());
    }
}
