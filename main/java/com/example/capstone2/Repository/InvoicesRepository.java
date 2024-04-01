package com.example.capstone2.Repository;

import com.example.capstone2.Model.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices,Integer> {
    Invoices findInvoicesById(Integer id);

    List<Invoices> findInvoicesByIssueDateBetweenOrDueDateBetween(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2);



}
