package com.crud.interviewsWebService.repository;

import com.crud.interviewsWebService.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    List<Company> findAll();
    List<Company> findByName(String name);
    List<Company> findByLocalization(String localization);
    Company save(Company company);
    void deleteById(Long id);
}
