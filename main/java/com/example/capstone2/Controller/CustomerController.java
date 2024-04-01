package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Customers;
import com.example.capstone2.Service.CustomersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomersService customersService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomers(){

        return ResponseEntity.status(200).body(customersService.getAllCustomers());
    }


    @PostMapping("/add")
    public ResponseEntity addCustomers(@RequestBody @Valid Customers customers, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);

        }
        customersService.addCustomer(customers);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id, @RequestBody @Valid Customers customers , Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }
        customersService.updateCustomer(id,customers);
        return ResponseEntity.status(200).body(new ApiResponse( "Customer updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customersService.deleteCustomer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted!"));
    }




    @GetMapping("/searchCustomerByEmail/{email}")
    public ResponseEntity searchCustomerByEmail (@PathVariable String email){

        return ResponseEntity.status(200).body(customersService.searchCustomerByEmail(email));

    }


    @PostMapping("/authenticate/{email}/{password}")
    public ResponseEntity authenticateCustomer(@PathVariable String email, @PathVariable String password) {
        customersService.authenticateCustomer(email,password);
        return ResponseEntity.status(200).body(new ApiResponse("Authentication successful!"));
    }

//
    @GetMapping("/topcustomers")
    public List<Customers> getTop3CustomersWithMostCarPurchases() {
        return customersService.getTop3CustomersWithMostCarPurchases();
    }



    // Endpoint to get customers older than a certain age
    @GetMapping("/customers/olderThan/{age}")
    public ResponseEntity getCustomersOlderThan(@PathVariable Integer age) {
        List<Customers> customers = customersService.getCustomersOlderThan(age);

        return ResponseEntity.status(200).body(customers);

    }


}
