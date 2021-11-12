package com.sample.project.sample.data;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sample.project.sample.model.CustomerModel;
import com.sample.project.sample.model.CustomerRequestModel;

@Service("hybridDataSource")
public class HybridDataSource implements Datasource<CustomerRequestModel, CustomerModel> {

	@Autowired(required = true)
	@Qualifier("remedyDataSource")
	private Datasource<CustomerRequestModel, CustomerModel> remedyDataSource;

	@Autowired(required = true)
	@Qualifier("dynamicsDataSource")
	private Datasource<CustomerRequestModel, CustomerModel> dynamicsDataSource;

	@Autowired(required = true)
	@Qualifier("baseDataSourceSelector")
	private DataSourceSelector<CustomerRequestModel, CustomerModel> hybridDataSourceSelector;

	@PostConstruct
	private void init() {
		Predicate<CustomerRequestModel> prospect = c -> c.getCustomerType().equalsIgnoreCase("Prospect");
		Predicate<CustomerRequestModel> customer = c -> c.getCustomerType().equalsIgnoreCase("Customer");

		hybridDataSourceSelector.addDatasource(remedyDataSource, prospect);
		hybridDataSourceSelector.addDatasource(dynamicsDataSource, customer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerModel create(CustomerRequestModel request) {
		return hybridDataSourceSelector.getDatasources(request).stream()
				.map(d -> ((Datasource<CustomerRequestModel, CustomerModel>) d).create(request))
				.collect(Collectors.toList()).get(0);
	}

	@Override
	public CustomerModel read(String id) {
		return (CustomerModel) hybridDataSourceSelector.getDatasources().stream().map(d -> d.read(id)).filter(c -> c !=null).findAny()
				.orElseGet(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerModel> read() {
		return (List<CustomerModel>) hybridDataSourceSelector.getDatasources().stream().map(Datasource::read)
				.flatMap(List::stream).collect(Collectors.toList());
	}

	@Override
	public CustomerModel update(String id, CustomerRequestModel request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		hybridDataSourceSelector.getDatasources(read(id)).forEach(d -> d.delete(id));
	}

}
