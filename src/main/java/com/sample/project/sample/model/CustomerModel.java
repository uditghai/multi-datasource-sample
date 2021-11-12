package com.sample.project.sample.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomerModel extends CustomerRequestModel {

	private String customerId;
	private LocalDate createdOn;

}
