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
public class Invoices {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "reservationId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer reservationId;

    @Column(columnDefinition = "date not null")
    private LocalDate issueDate;

    @Column(columnDefinition = "date not null")
    private LocalDate dueDate;



    @NotNull(message = "amountDue cannot be null")
    @Column(columnDefinition = "double not null")
    private Double amountDue;

    @NotNull(message = "paymentStatus cannot be null")
    @Column(columnDefinition = "boolean default false not null")
    private Boolean paymentStatus = true;





















}
