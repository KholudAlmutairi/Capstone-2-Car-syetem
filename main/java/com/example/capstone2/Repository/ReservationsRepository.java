package com.example.capstone2.Repository;

import com.example.capstone2.Model.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations,Integer> {
    Reservations findReservationsById(Integer id);


    @Query("select a from Reservations a where a.startDate=?1")
    List<Reservations> findReservationsByStartDate(LocalDate startDate);

    List<Reservations> findReservationsByCustomerId(Integer customerId);
    int countByCarId(Integer carId);

    List<Reservations> findReservationsByStartDateBetweenOrEndDateBetween(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2);


}
