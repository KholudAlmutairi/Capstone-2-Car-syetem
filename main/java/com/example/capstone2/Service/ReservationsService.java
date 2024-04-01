package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Car;
import com.example.capstone2.Model.Customers;
import com.example.capstone2.Model.Reservations;
import com.example.capstone2.Repository.CarRepository;
import com.example.capstone2.Repository.CustomersRepository;
import com.example.capstone2.Repository.ReservationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationsService {


    private final ReservationsRepository reservationsRepository;
    private final CarRepository carRepository;
    private final CustomersRepository customersRepository;

    //• Get all the Reservations
    public List<Reservations> getAllReservations() {

        return reservationsRepository.findAll();
    }


    //• Add new Reservations

    public void addReservations(Reservations reservations) {
        Car car = carRepository.findCarById(reservations.getCarId());
        Customers customer = customersRepository.findCustomersById(reservations.getCustomerId());

        if (car == null) {
            throw new ApiException("Car Id found");
        }
        if (customer == null) {
            throw new ApiException("Customer Id not found");
        }
        car.setAvailable(false);
        customer.setPurchasesCount(customer.getPurchasesCount()+1);
        reservationsRepository.save(reservations);

    }

    //• Update Reservations
    public void updateReservations(Integer id, Reservations reservations) {
        Reservations r = reservationsRepository.findReservationsById(id);
        Car car = carRepository.findCarById(reservations.getCarId());
        Customers customer = customersRepository.findCustomersById(reservations.getCustomerId());
        if (r == null) {
            throw new ApiException("id not found");
        }

        if (car == null) {
            throw new ApiException("Car Id found");
        }
        if (customer == null) {
            throw new ApiException("Customer Id not found");
        }

        r.setCarId(reservations.getCarId());
        r.setCustomerId(reservations.getCustomerId());
        r.setStartDate(reservations.getStartDate());
        r.setEndDate(reservations.getEndDate());
        r.setTotalCost(reservations.getTotalCost());
        reservationsRepository.save(r);
    }

    //• Delete Reservations
    public void deleteReservations(Integer id) {
        Reservations reservations = reservationsRepository.findReservationsById(id);
        if (reservations == null) {
            throw new ApiException("id not found");
        }
        reservationsRepository.delete(reservations);

    }


    //endpoint #1


    public List<Reservations> getReservationsByStarDate(LocalDate starDate) {
        List<Reservations> r = reservationsRepository.findReservationsByStartDate(starDate);
        if (r == null) {
            throw new ApiException("Reservation not found");
        }
        return r;

    }


    //endpoint #2


    public Reservations getReservationById(Integer id) {
        Reservations reservation = reservationsRepository.findReservationsById(id);

        if (reservation == null) {
            throw new ApiException("Reservation not found");
        }
        return reservation;
    }




    //endpoint #3


    public List<Reservations> getReservationsByCustomerId(Integer customerId) {
        List<Reservations> r = reservationsRepository.findReservationsByCustomerId(customerId);

        if (r == null) {
            throw new ApiException("Reservation not found");
        }

        return r;
    }


    //endpoint #4

    public double calculateTotalCostOfCustomerReservations(Integer customerId) {

        List<Reservations> reservations = getReservationsByCustomerId(customerId);
        double totalCost = 0.0;
        for (Reservations reservation : reservations) {
            totalCost += reservation.getTotalCost();
        }

        return totalCost;
    }




    //endpoint #8
//    public double calculateReservationCost(Car car, LocalDate startDate, LocalDate endDate) {
//
//        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
//            throw new ApiException("Invalid dates provided");
//        }
//
//        // حساب عدد الأيام بين التواريخ
//        long numberOfDays = ((endDate.toEpochDay() - startDate.toEpochDay()) + 1);
//
//
//        if (!car.getAvailable()) {
//            throw new ApiException("Car is not available");
//        }
//
//        // حساب تكلفة الحجز
//        double reservationCost = numberOfDays * car.getPricePerDay();
//
//        return reservationCost;
//    }




    //endpoint #5
    public double calculateReservationCost(Integer carId, Integer numberOfDays) {
        Car car =carRepository.findCarById(carId);
        if (car == null) {
            throw new ApiException("Wrong id");
        }
        double reservationCost =  car.getPricePerDay()*numberOfDays;
        return reservationCost;

    }


    //endpoint #6

    //• Update End Date for Reservation
    public void updateReservationEndDate(Integer id, LocalDate endDate) {
        Reservations reservation = reservationsRepository.findReservationsById(id);

        if (reservation == null) {
            throw new ApiException("Reservation not found");
        }

        if (endDate.isBefore(reservation.getStartDate())) {
            throw new ApiException("End date cannot be before start date");
        }

        reservation.setEndDate(endDate);
        reservationsRepository.save(reservation);
    }


    //endpoint #10

    public void checkAndCancelReservation(Integer reservationId) {
        Reservations reservation = reservationsRepository.findReservationsById(reservationId);
        if (reservation == null) {
            throw new ApiException("Reservation not found");
        }

        LocalDate currentDate = LocalDate.now(); //  على تاريخ اليوم الحالي
        if (currentDate.isAfter(reservation.getEndDate())) { // مقارنة تاريخ اليوم بتاريخ انتهاء الحجز
            throw new ApiException("Reservation already ended");
        }


       // تحقق مما إذا كان الحجز مدفوعًا
        if (!reservation.getPaid()) {
            // إلغاء الحجز إذا لم يتم دفع المبلغ
            reservation.setCancelled(true);
            reservationsRepository.save(reservation);
        }
    }







    //endpoint #11
//    public void applyDiscountForDate(LocalDate date, double discountAmount) {
//
//        // لست  بالحجوزات لهذا التاريخ
//        List<Reservations> reservations = reservationsRepository.findReservationsByStartDate(date);
//
//
//
//        if (!reservations.isEmpty()) {
//            // حساب المبلغ الذي سيتم خصمه من كل حجز
//            double discountPerReservation = discountAmount / reservations.size();
//
//            //  الخصم على كل حجز
//            for (Reservations reservation : reservations) {
//                double totalCost = reservation.getTotalCost();
//                totalCost -= discountPerReservation;
//                reservation.setTotalCost(totalCost);
//                reservationsRepository.save(reservation);
//            }
//        }
    }
