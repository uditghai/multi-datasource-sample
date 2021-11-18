package com.sample.project.sample.utils;

import com.sample.project.sample.entity.CustomerEntity;
import com.sample.project.sample.model.CustomerModel;
import org.springframework.stereotype.Service;

@Service
public class CustomerUtil {
    public CustomerModel toCustomerModel(CustomerEntity customerEntity) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerId(customerEntity.getCustomerId());
        customerModel.setCustomerType(customerEntity.getCustomerType());
        customerModel.setCustomerName(customerEntity.getCustomerName());
        customerModel.setCreatedOn(customerEntity.getCreatedOn());

        return customerModel;
    }

    public CustomerEntity toCustomerEntity(CustomerModel customerModel) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customerModel.getCustomerId());
        customerEntity.setCustomerType(customerModel.getCustomerType());
        customerEntity.setCustomerName(customerModel.getCustomerName());
        customerEntity.setCreatedOn(customerModel.getCreatedOn());
        return customerEntity;
    }
}
