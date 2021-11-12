package com.sample.project.sample.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sample.project.sample.model.CustomerModel;
import com.sample.project.sample.model.CustomerRequestModel;

@Service("remedyDataSource")
public class RemedyDataSource implements Datasource<CustomerRequestModel, CustomerModel> {

	private Map<String, CustomerModel> customers = new HashMap<String, CustomerModel>();

	@Override
	public CustomerModel create(CustomerRequestModel request) {
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerName(request.getCustomerName());
		customerModel.setCustomerType(request.getCustomerType());
		customerModel.setCustomerId(UUID.randomUUID().toString());
		customerModel.setCreatedOn(LocalDate.now());
		customers.put(customerModel.getCustomerId(), customerModel);
		return customerModel;
	}

	@Override
	public CustomerModel read(String id) {
		return customers.get(id);
	}

	@Override
	public List<CustomerModel> read() {
		return new ArrayList<CustomerModel>(customers.values());
	}

	@Override
	public CustomerModel update(String id, CustomerRequestModel request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		customers.remove(id);
	}
}
