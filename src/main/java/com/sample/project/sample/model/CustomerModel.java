package com.sample.project.sample.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerModel extends CustomerRequestModel {
    private String customerId;

    private LocalDate createdOn;
}
