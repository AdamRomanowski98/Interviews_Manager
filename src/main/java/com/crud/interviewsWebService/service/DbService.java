package com.crud.interviewsWebService.service;

import com.crud.interviewsWebService.domain.Company;
import com.crud.interviewsWebService.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<Company> getFiltered(Specification<Company> specification){
        return companyRepository.findAll(specification);
    }


    public Specification<Company> getSpec(Company company){
        return((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(company.getName()!=null && !(company.getName().isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + company.getName() + "%"));
            }
            if(company.getLocalization()!=null && !(company.getLocalization().isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("localization"), company.getLocalization()));
            }
            if(company.getFromSalary() != null){
                predicates.add(criteriaBuilder.equal(root.get("fromSalary"), company.getFromSalary() ));
            }
            if(company.getToSalary() != null){
                predicates.add(criteriaBuilder.equal(root.get("toSalary"), company.getToSalary()));
            }
            if(company.getTypeOfContract() != null && !(company.getTypeOfContract().isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("typeOfContract"), "%" + company.getTypeOfContract() + "%"));
            }
            if(company.getMustHaveTechnologies() != null && !(company.getMustHaveTechnologies().isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("mustHaveTechnologies"), "%" +company.getMustHaveTechnologies() + "%"));
            }

//            if(company.getMustHaveTechnologies() != null && !(company.getMustHaveTechnologies().isEmpty())){
//                for(String skill : company.getMustHaveTechnologies()){
//                    predicates.add(criteriaBuilder.equal(root.get("mustHaveTechnologies"), skill));
//                }
//            }

            if(company.getNiceToHaveTechnologies() != null && !(company.getNiceToHaveTechnologies().isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("niceToHaveTechnologies"), "%" +company.getNiceToHaveTechnologies() + "%"));
            }
            if(company.getInterviewDescription() != null && !(company.getInterviewDescription().isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("interviewDescription"), "%" +company.getInterviewDescription() + "%"));
            }
            if(company.getDateOfInterview() != null){
                predicates.add(criteriaBuilder.like(root.get("dateOfInterview"), "%" + company.getDateOfInterview() +"%"));
            }
            if(company.getAnswered() != null){
                predicates.add(criteriaBuilder.equal(root.get("answered"), company.getAnswered()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }



    public void deleteCompanyById(final Long id){
        companyRepository.deleteById(id);
    }


}
