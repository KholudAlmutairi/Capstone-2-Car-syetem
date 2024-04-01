package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Car;
import com.example.capstone2.Model.Reservations;
import com.example.capstone2.Repository.CarRepository;
import com.example.capstone2.Repository.ReservationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CarService {

    private final CarRepository carRepository;
    private  final ReservationsRepository reservationsRepository;


    //• Get all the Cars
    public List<Car> getAllCars() {

        return carRepository.findAll();
    }

    //• Add new Car
    public void addCar(Car car) {
        carRepository.save(car);
    }


    //• Update Car
    public void updateCar(Integer id, Car car) {
        Car c = carRepository.findCarById(id);
        if (c == null) {
            throw new ApiException("Wrong id");
        }
        c.setCarName(car.getCarName());
        c.setModel(car.getModel());
        c.setMake(car.getMake());
        c.setYear(car.getYear());
        c.setColor(car.getColor());
        c.setTransmissionType(car.getTransmissionType());
        c.setPricePerDay(car.getPricePerDay());
        c.setAvailable(car.getAvailable());
        carRepository.save(c);
    }


    //• Delete Car
    public void deleteCar(Integer id) {
        Car car =carRepository.findCarById(id);
        if (car == null) {
            throw new ApiException("Wrong id");
        }
        carRepository.delete(car);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////


    // endpoint #1
    public List<Car> getCarByName(String carName) {
        List<Car> c= carRepository.findCarByCarName(carName) ;
        if (c == null) {
            throw new ApiException("Car not found");
        }
        return c;
    }
    // endpoint #2

    public List<Car> getAvailableCars() {
        List<Car> c = carRepository.availableCars();

        if (c == null) {
            throw new ApiException("Car not found");
        }
        List<Car> c2 = carRepository.availableCars();
        for(Car car:c){
            if(car.getAvailable().equals(true))
                c2.add(car);

        }
        return c2;
    }



    // endpoint #3

    public Car getMostRentedCar() {
        List<Car> allCars = carRepository.findAll();
        if (allCars.isEmpty()) {
            throw new ApiException("No cars available");
        }

        Car mostRentedCar = null;
        Integer maxRentals = 0;

        for (Car car : allCars) {
            Integer numberOfRentals = reservationsRepository.countByCarId(car.getId());
            if (numberOfRentals > maxRentals) {
                maxRentals = numberOfRentals;
                mostRentedCar = car;
            }
        }

        if (mostRentedCar == null) {
            throw new ApiException("No rentals found");
        }

        return mostRentedCar;
    }


    // endpoint #4
    public List<Car> getCarByYear(Integer year) {
        List<Car> c = carRepository.findCarByYear(year);
        if (c == null) {
            throw new ApiException("Car not found");
        }
        return c;
    }



    // endpoint #5
        public void applyDiscountForYear(Integer year, double discountPercentage) {
        for (Car car : getCarByYear(year)) {

        double discountedPrice = car.getPricePerDay() * (1 - (discountPercentage / 100));
        car.setPricePerDay(discountedPrice);
        carRepository.save(car);
        }
    }





}







