package com.sample.project.sample.data;

import org.springframework.stereotype.Service;

import com.sample.project.sample.model.CustomerModel;
import com.sample.project.sample.model.CustomerRequestModel;

@Service("baseDataSourceSelector")
public class BaseDataSourceSelector implements DataSourceSelector<CustomerRequestModel, CustomerModel> {

}
