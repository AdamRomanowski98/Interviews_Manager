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

    public Specification<Company> getSpec(String name, String localization, double fromSalary, Double toSalary, String typeOfContract, Set<String> mustHaveTechnologies,
                                          Set<String> niceToHaveTechnologies, Boolean answered, String interviewDescription, LocalDate dateOfInterview ){
        return((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(name !=null && !(name.isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if(localization!=null && !(localization.isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("localization"), "%" + localization + "%"));
            }
            if(fromSalary != 0.0){
                predicates.add(criteriaBuilder.like(root.get("fromSalary"), "%" + fromSalary + "%"));
            }
            if(toSalary != null){
                predicates.add(criteriaBuilder.like(root.get("toSalary"), "%" + toSalary + "%"));
            }
            if(typeOfContract != null && !(typeOfContract.isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("typeOfContract"), "%" + typeOfContract + "%"));
            }
            if(mustHaveTechnologies != null && !(mustHaveTechnologies.isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("mustHaveTechnologies"), "%" +mustHaveTechnologies + "%"));
            }
            if(niceToHaveTechnologies != null && !(niceToHaveTechnologies.isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("niceToHaveTechnologies"), "%" +niceToHaveTechnologies + "%"));
            }
            if(interviewDescription != null && !(interviewDescription.isEmpty())){
                predicates.add(criteriaBuilder.like(root.get("interviewDescription"), "%" +interviewDescription + "%"));
            }
            if(dateOfInterview != null){
                predicates.add(criteriaBuilder.like(root.get("dateOfInterview"), "%" + dateOfInterview +"%"));
            }
            if(answered != null){
                predicates.add(criteriaBuilder.like(root.get("answered"), "%" +answered + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    public List<Company> getFiltered(Specification<Company> specification){
        return companyRepository.findAll(specification);
    }


//    public Specification<Company> getSpec(Company company){
//        return((root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            if(company.getName()!=null && !(company.getName().isEmpty())){
//                predicates.add(criteriaBuilder.like(root.get("name"), "%" + company.getName() + "%"));
//            }
//            if(company.getLocalization()!=null && !(company.getLocalization().isEmpty())){
//                predicates.add(criteriaBuilder.like(root.get("localization"), "%" + company.getLocalization() + "%"));
//            }
//            if(company.getFromSalary() != 0.0){
//                predicates.add(criteriaBuilder.like(root.get("fromSalary"), "%" + company.getFromSalary() + "%"));
//            }
//            if(company.getToSalary() != 0.0){
//                predicates.add(criteriaBuilder.like(root.get("toSalary"), "%" + company.getToSalary() + "%"));
//            }
//            if(company.getTypeOfContract() != null && !(company.getTypeOfContract().isEmpty())){
//                predicates.add(criteriaBuilder.like(root.get("typeOfContract"), "%" + company.getTypeOfContract() + "%"));
//            }
//            if(company.getMustHaveTechnologies() != null && !(company.getMustHaveTechnologies().isEmpty())){
//                predicates.add(criteriaBuilder.like(root.get("mustHaveTechnologies"), "%" +company.getMustHaveTechnologies() + "%"));
//            }
//            if(company.getNiceToHaveTechnologies() != null && !(company.getNiceToHaveTechnologies().isEmpty())){
//                predicates.add(criteriaBuilder.like(root.get("niceToHaveTechnologies"), "%" +company.getNiceToHaveTechnologies() + "%"));
//            }
//            if(company.getInterviewDescription() != null && !(company.getInterviewDescription().isEmpty())){
//                predicates.add(criteriaBuilder.like(root.get("interviewDescription"), "%" +company.getInterviewDescription() + "%"));
//            }
//            if(company.getDateOfInterview() != null){
//                predicates.add(criteriaBuilder.like(root.get("dateOfInterview"), "%" + company.getDateOfInterview() +"%"));
//            }
//            predicates.add(criteriaBuilder.like(root.get("answered"), "%" +company.isAnswered() + "%"));
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        });
//    }



    public void deleteCompanyById(final Long id){
        companyRepository.deleteById(id);
    }


}
