package com.crud.interviewsWebService.repository;

import com.crud.interviewsWebService.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface CompanyRepository extends CrudRepository<Company, Long> {

    List<Company> findAll();
    List<Company> findByName(String name);
    List<Company> findByLocalization(String localization);
    Company save(Company company);

    List<Company> findCompaniesByAnswered(boolean answered);

    void deleteById(Long id);
}
