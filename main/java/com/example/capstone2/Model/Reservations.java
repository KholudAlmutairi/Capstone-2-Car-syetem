package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Reservations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "carId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer carId;


    @NotNull(message = "customerId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer customerId;


    @Column(columnDefinition = "date")
    private LocalDate startDate;


   @Column(columnDefinition = "date ")
    private LocalDate endDate;


   // @Column(columnDefinition = "int")
    private Integer numberOfDays;


    @NotNull(message = "totalCost cannot be null")
    @Column(columnDefinition = "double not null")
    private Double totalCost;

    @NotNull(message = "cancelled field cannot be null")
    @Column(columnDefinition = "boolean not null default false")
    private Boolean cancelled;

    @NotNull(message = "paid field cannot be null")
    @Column(columnDefinition = "boolean not null default false")
    private Boolean paid;






}
