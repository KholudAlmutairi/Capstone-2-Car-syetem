package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Employees;
import com.example.capstone2.Repository.EmployeesReopsitory;
import com.example.capstone2.Service.EmployeesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;

    @GetMapping("/get")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(200).body(employeesService.getAllEmployees());}


    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employees employees, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);

        }
        employeesService.addEmployees(employees);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable Integer id, @RequestBody @Valid Employees employees , Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }
        employeesService.updateEmployees(id,employees);
        return ResponseEntity.status(200).body(new ApiResponse( "Employee updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id){
        employeesService.deleteEmployees(id);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted!"));
    }


//    @PostMapping("/calculate-sales-commission/{employeeId}/{salesPercentage}")
//    public ResponseEntity<String> calculateSalesCommissionAndIncreaseSalary(
//            @PathVariable Integer employeeId,
//            @PathVariable Double salesPercentage,
//            Errors errors) {
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.badRequest().body(message);
//        }
//
//        employeesService.calculateSalesCommissionAndIncreaseSalary(employeeId, salesPercentage);
//        return ResponseEntity.ok("Sales commission calculated and salary increased successfully");
//    }




















}
