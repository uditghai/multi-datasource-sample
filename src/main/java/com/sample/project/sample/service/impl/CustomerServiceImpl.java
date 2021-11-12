package com.sample.project.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sample.project.sample.data.Datasource;
import com.sample.project.sample.model.CustomerModel;
import com.sample.project.sample.model.CustomerRequestModel;
import com.sample.project.sample.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	@Qualifier("hybridDataSource")
	Datasource<CustomerRequestModel, CustomerModel> hybridDataSource;

	@Override
	public CustomerModel create(CustomerRequestModel request) {
		return hybridDataSource.create(request);
	}

	@Override
	public CustomerModel read(String id) {
		return hybridDataSource.read(id);
	}

	@Override
	public List<CustomerModel> read() {
		return hybridDataSource.read();
	}

	@Override
	public CustomerModel update(String id, CustomerRequestModel request) {
		return hybridDataSource.update(id, request);
	}

	@Override
	public void delete(String id) {
		hybridDataSource.delete(id);
	}

}
