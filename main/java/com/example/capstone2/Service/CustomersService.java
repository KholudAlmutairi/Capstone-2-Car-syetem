package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Customers;
import com.example.capstone2.Repository.CustomersRepository;
import com.example.capstone2.Repository.ReservationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomersService {




    private final CustomersRepository customersRepository;
    private  final ReservationsRepository reservationsRepository;

    //• Get all the Customers
    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }


    //• Add new  Customer
    public void addCustomer(Customers customers) {

        customersRepository.save(customers);
    }


    //• Update Customer
    public void updateCustomer(Integer id,Customers customers) {
        Customers c=customersRepository.findCustomersById(id);

        if (c == null) {
            throw new ApiException("Wrong id");
        }
        c.setFullName(customers.getFullName());
        c.setAge(customers.getAge());
        c.setEmail(customers.getEmail());
        c.setPassword(customers.getPassword());
        c.setPhoneNumber(customers.getPhoneNumber());
        c.setAddress(customers.getAddress());
        customersRepository.save(c);

}

    //• Delete Customer
    public void deleteCustomer(Integer id) {
        Customers c=customersRepository.findCustomersById(id);
        if (c == null) {
            throw new ApiException("Wrong id");
        }
        customersRepository.delete(c);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // endpoint #1
    public List<Customers> getCustomersOlderThan(Integer age) {
        return customersRepository.findByAgeGreaterThan(age);}


    // endpoint #2
    public Customers searchCustomerByEmail(String email){
        Customers customers=customersRepository.findCustomersByEmail(email);
        if (customers == null) {
            throw new ApiException("customer not found");
        }
        return customers;
    }


    //• Check if email and password are correct
    // endpoint #3
    public Customers authenticateCustomer(String email, String password) {
        Customers customers=customersRepository.authenticateCustomer(email, password);
        if (customers == null) {
            throw new ApiException("Customer not found");
        }
        return customers;

    }

    // endpoint #4

    public List<Customers> getTop3CustomersWithMostCarPurchases() {
        return customersRepository.findTop3ByOrderByCarPurchasesDesc();
    }




}








