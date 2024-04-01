package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Car;
import com.example.capstone2.Service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;


    @GetMapping("/get")
    public ResponseEntity getAllCars() {

        return ResponseEntity.status(200).body(carService.getAllCars());
    }


    @PostMapping("/add")
    public ResponseEntity addCar(@RequestBody @Valid Car car, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);

        }
        carService.addCar(car);
        return ResponseEntity.status(200).body(new ApiResponse("Car Added"));

    }

    // Update Car
    @PutMapping("/update/{id}")
    public ResponseEntity updateCar(@PathVariable Integer id, @RequestBody @Valid Car car, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }

        carService.updateCar(id, car);
        return ResponseEntity.status(200).body(new ApiResponse("Car updated"));
    }

    //Delete Car
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        carService.deleteCar(id);
        return ResponseEntity.status(200).body(new ApiResponse("Car deleted!"));
    }


    @GetMapping("/getCarByName/{carName}")
    public ResponseEntity getCarByName(@PathVariable String carName) {
        return ResponseEntity.status(200).body(carService.getCarByName(carName));
    }

//
    @GetMapping("/availableCar")
    public ResponseEntity availableCars(){
        List<Car> availableCars = carService.getAvailableCars();
        return ResponseEntity.ok(availableCars);
    }







//    @GetMapping("/availableCar/{startDate}/{endtDate}")
//    public ResponseEntity getAvailableCars(@PathVariable LocalDate startDate, LocalDate endDate) {
//        List<Car> availableCars = carService.getAvailableCars(startDate,endDate);
//        return ResponseEntity.ok(availableCars);
//    }

    @GetMapping("/most-rented-car")
    public ResponseEntity getMostRentedCar() {
        Car mostRentedCar = carService.getMostRentedCar();
        return ResponseEntity.ok(mostRentedCar);
    }



    @GetMapping("/getCarByYear/{year}")
    public ResponseEntity getCarByYear(@PathVariable Integer year) {
        return ResponseEntity.status(200).body(carService.getCarByYear(year));
    }





//
    @PostMapping("/applydiscount/{year}/{discountPercentage}")

    public ResponseEntity applyDiscountForYear(@PathVariable Integer year,
                                               @PathVariable double discountPercentage) {
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(message);
//        }

        // Call applyDiscountForYear method from carService
        carService.applyDiscountForYear(year, discountPercentage);

        return ResponseEntity.ok("Discount applied successfully");
    }












//    @PostMapping("/applydiscount/{year}/{discountPercentage}")
//    public ResponseEntity applyDiscountForYear(@PathVariable int year,
//                                               @PathVariable double discountPercentage,
//                                               Errors errors) {
//        if(errors.hasErrors()){
//            String message=errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(message);
//        }
//
//        // Call applyDiscountForYear method from carService
//        carService.applyDiscountForYear(year, discountPercentage);
//
//        return ResponseEntity.ok("Discount applied successfully");
//    }





//
//
//    @PostMapping("/applydiscount/{year}/{discountPercentage}")
//    public ResponseEntity applyDiscountForYear(@PathVariable int year,
//                                                       @PathVariable double discountPercentage,Errors errors) {
//
//        if(errors.hasErrors()){
//            String message=errors.getFieldError().getDefaultMessage();
//
//            return ResponseEntity.status(400).body(message);
//        }
//            carService.applyDiscountForYear(year, discountPercentage);
//            return ResponseEntity.ok("Discount applied successfully");
//
//    }



    //Endpoint 2
//    public void applyDiscount(String categoryId, double discountPercentage) {
//        for (Product product : getProductsByCategory(categoryId)) {
//            double discountedPrice = product.getPrice() * (1 - (discountPercentage / 100));
//            product.setPrice(discountedPrice);
//        }
//    }
//
//
//
//    //Endpoint 1
//    public list<Car> getCarsByYear(String categoryId) {
//        ArrayList<Product> productsByCategory = new ArrayList<>();
//        for (Product product : products) {
//            if (product.getCategoryID().equalsIgnoreCase(categoryId)) {
//                productsByCategory.add(product);
//            }
//        }
//        return productsByCategory;
//    }



}
