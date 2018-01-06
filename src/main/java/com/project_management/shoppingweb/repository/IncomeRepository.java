package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {
    Income getById(long id);
    Income save(Income income);
    List<Income> getAllBySellerId(long sellerId);
    void  deleteById(long Id);
    List<Income> findAll();

}
