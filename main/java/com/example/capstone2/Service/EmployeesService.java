package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Car;
import com.example.capstone2.Model.Employees;
import com.example.capstone2.Repository.CarRepository;
import com.example.capstone2.Repository.EmployeesReopsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeesService {

    private final EmployeesReopsitory employeesReopsitory;
    private final  CarRepository carRepository;

    //• Get all the Employees
    public List<Employees> getAllEmployees() {

        return employeesReopsitory.findAll();
    }

    //• Add new Employees
    public void addEmployees(Employees employees) {
        employeesReopsitory.save(employees);
    }

    //• Update Employees
    public void updateEmployees(Integer id,Employees employees) {
        Employees e =employeesReopsitory.findEmployeesById(id);
        if (e == null) {
            throw new ApiException("Wrong id");
        }

        e.setCarId(employees.getCarId());
        e.setFullName(employees.getFullName());
        e.setEmail(employees.getEmail());
        e.setPhoneNumber(employees.getPhoneNumber());
        e.setPosition(employees.getPosition());
        e.setBalance(employees.getBalance());
        e.setHireDate(employees.getHireDate());
        e.setSalePercentage(employees.getSalePercentage());

        employeesReopsitory.save(e);

    }


    //• Delete Employees
    public void deleteEmployees(Integer id) {
        Employees e =employeesReopsitory.findEmployeesById(id);
        if (e == null) {
            throw new ApiException("Wrong id");
        }
        employeesReopsitory.delete(e);



    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////


    // endpoint #16
//    public void calculateSalesCommissionAndIncreaseSalary(Integer employeeId, Double salesPercentage) {
//        // يجيب الموظف
//        Employees employee = employeesReopsitory.findEmployeesById(employeeId);
//        if (employee == null) {
//            throw new ApiException("Employee not found");
//        }
//
//        //السيارات اللي تم بيعها بواسطة موظف
//        Car soldCars = carRepository.findCarsByEmployeeId(employeeId);

        // حساب عدد السيارات المباعة
//        int numberOfSoldCars = soldCars.size();

        // حساب المبلغ اللي يأخذه الموظف
//        double commission = numberOfSoldCars * salesPercentage;

        // زيادة الراتب للموظف بالمبلغ المحسوب
//        double currentSalary = employee.getBalance();
//        double updatedSalary = currentSalary + commission;
//        employee.setBalance(updatedSalary);
//
//        employeesReopsitory.save(employee);
    }


