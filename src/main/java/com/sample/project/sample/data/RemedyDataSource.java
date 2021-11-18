package com.sample.project.sample.data;

import com.sample.project.sample.model.CustomerModel;
import com.sample.project.sample.model.CustomerRequestModel;
import com.sample.project.sample.utils.CustomerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("remedyDataSource")
public class RemedyDataSource implements Datasource<CustomerRequestModel, CustomerModel> {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CustomerUtil customerUtil;
    String customerTypeFilter = "prospect";

    @Override
    public CustomerModel create(CustomerRequestModel request) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerName(request.getCustomerName());
        customerModel.setCustomerType(request.getCustomerType().toLowerCase(Locale.ROOT));
        customerModel.setCustomerId(UUID.randomUUID().toString());
        customerModel.setCreatedOn(LocalDate.now());

        repository.save(customerUtil.toCustomerEntity(customerModel));
        return customerModel;
    }

    @Override
    public CustomerModel read(String id) {
        return customerUtil.toCustomerModel(
                repository.findById(id).orElseThrow());
    }

    @Override
    public List<CustomerModel> read() {
        return repository.findByCustomerType(customerTypeFilter).stream().map(customerUtil::toCustomerModel).collect(Collectors.toList());
    }

    @Override
    public CustomerModel update(String id, CustomerRequestModel request) {
        return null;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
