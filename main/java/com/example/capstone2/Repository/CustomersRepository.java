package com.example.capstone2.Repository;

import com.example.capstone2.Model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepository  extends JpaRepository<Customers,Integer> {
 Customers findCustomersById(Integer id);
 Customers findCustomersByEmail(String email);

 @Query("select a from Customers a where a.email=?1 and a.password=?2")
 Customers authenticateCustomer(String email,String password);


 @Query("SELECT c FROM Customers c ORDER BY c.purchasesCount DESC")
 List<Customers> findTop3ByOrderByCarPurchasesDesc();


 List<Customers> findByAgeGreaterThan(Integer age);


}
