package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Car;
import com.example.capstone2.Model.Reservations;
import com.example.capstone2.Service.ReservationsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationsController {


    private final ReservationsService reservationsService;

    @GetMapping("/get")
    public ResponseEntity getAllReservations() {

        return ResponseEntity.status(200).body(reservationsService.getAllReservations());
    }


    @PostMapping("/add")
    public ResponseEntity addReservations(@RequestBody @Valid Reservations reservations, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        reservationsService.addReservations(reservations);
        return ResponseEntity.status(200).body(new ApiResponse("Reservations Added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateReservations(@PathVariable Integer id, @RequestBody @Valid Reservations reservations, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }

        reservationsService.updateReservations(id, reservations);
        return ResponseEntity.status(200).body(new ApiResponse("Reservation updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReservations(@PathVariable Integer id) {
        reservationsService.deleteReservations(id);
        return ResponseEntity.status(200).body(new ApiResponse("Reservations deleted!"));
    }


    @GetMapping("/getReservationsByStarDate/{starDate}")
    public ResponseEntity getReservationsByStarDate(@PathVariable LocalDate starDate) {
        return ResponseEntity.status(200).body(reservationsService.getReservationsByStarDate(starDate));

    }

    ////////////////////////////////////////////////////////////////////////////////


    // Endpoint للحصول على حجز معين بواسطة معرف الحجز
    @GetMapping("/reservations/{id}")
    public ResponseEntity getReservationById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(reservationsService.getReservationById(id));
    }


    // Endpoint للحصول على جميع حجوزات عميل معين بواسطة معرف العميل
    @GetMapping("/reservations/customerId")
    public ResponseEntity getReservationsByCustomerId(@PathVariable Integer customerId) {
        return ResponseEntity.status(200).body(reservationsService.getReservationsByCustomerId(customerId));

    }


//    @PostMapping("/calculateReservationCost/ {LocalDate}/{startDate}/{LocalDate}/{startDate}")
//    public ResponseEntity calculateReservationCost(@RequestBody Car car, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate,Errors errors) {
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(400).body(message);
//        }
//
//        // حساب تكلفة الحجز
//        double reservationCost = reservationsService.calculateReservationCost(car, startDate, endDate);
//        return ResponseEntity.status(200).body(new ApiResponse("Reservation cost calculated: " + reservationCost));
//
//    }


    @PostMapping("/calculateReservationCost/{carId}/{numberOfDays}")
    public ResponseEntity calculateReservationCost(@PathVariable Integer carId,@PathVariable Integer numberOfDays) {
        // حساب تكلفة الحجز
        double reservationCost = reservationsService.calculateReservationCost(carId,numberOfDays);
        return ResponseEntity.status(200).body(new ApiResponse("Reservation cost calculated: " + reservationCost));
    }


    @PutMapping("/{id}/endDate")
    public ResponseEntity updateReservationEndDate(@PathVariable Integer id, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        reservationsService.updateReservationEndDate(id, endDate);
        return ResponseEntity.status(200).body(new ApiResponse("Reservation end date updated successfully"));
    }


    @GetMapping("/customers/{customerId}/totalCostOfReservations")
    public ResponseEntity<Double> getTotalCostOfCustomerReservations(@PathVariable Integer customerId) {
        double totalCost = reservationsService.calculateTotalCostOfCustomerReservations(customerId);
        return ResponseEntity.ok(totalCost);
    }

//
//
//
    @DeleteMapping("/reservations/{id}/checkAndCancel")
    public ResponseEntity checkAndCancelReservation(@PathVariable Integer id) {
        reservationsService.checkAndCancelReservation(id);
        return ResponseEntity.ok("Reservation checked and possibly cancelled successfully");
    }
//
//    @GetMapping("/apply-discount/{date}/{discountAmount}")
//    public ResponseEntity applyDiscountForDate(@PathVariable("date") String date,
//                                               @PathVariable("discountAmount") double discountAmount
//    ) {
//        LocalDate parsedDate = LocalDate.parse(date);
//        reservationsService.applyDiscountForDate(parsedDate, discountAmount);
//        return ResponseEntity.status(200).body("Discount applied successfully");
//    }

























}
