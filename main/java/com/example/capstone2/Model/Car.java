package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "carName cannot be null")
    @Column(columnDefinition = "varchar(25) not null")
    private String carName;

    @NotEmpty(message = "model cannot be null")
    @Column(columnDefinition = "varchar(25) not null")
    private String model;

    @NotEmpty(message = "make cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String make;

    @NotNull(message = "year cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer year;

    @NotEmpty(message = "color cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String color;

    @NotEmpty(message = "transmission Type cannot be null")
    @Column(columnDefinition = "varchar(30) not null")
    private String transmissionType;

    @NotNull(message = "price_per_day cannot be null")
    @Column(columnDefinition = "double not null")
    private Double pricePerDay;

//    @NotNull(message = "available cannot be null")
//    @Column(columnDefinition = "boolean default true not null")
//    private Boolean available = true;



    @NotNull(message = "available cannot be null")
    @Column(columnDefinition = "boolean not null")
    private Boolean available;











}
