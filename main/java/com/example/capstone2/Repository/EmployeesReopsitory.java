package com.example.capstone2.Repository;

import com.example.capstone2.Model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesReopsitory extends JpaRepository<Employees,Integer> {
    Employees findEmployeesById(Integer id);
}
