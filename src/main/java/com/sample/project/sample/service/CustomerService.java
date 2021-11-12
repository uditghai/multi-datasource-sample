package com.sample.project.sample.service;

import java.util.List;

import com.sample.project.sample.model.CustomerModel;
import com.sample.project.sample.model.CustomerRequestModel;

public interface CustomerService {

	CustomerModel create(CustomerRequestModel request);

	CustomerModel read(String id);

	List<CustomerModel> read();

	CustomerModel update(String id, CustomerRequestModel request);

	void delete(String id);

}
