package com.sample.project.sample.data;

import com.sample.project.sample.model.CustomerModel;
import com.sample.project.sample.model.CustomerRequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("baseDataSourceSelector")
public class BaseDataSourceSelector implements DataSourceSelector<CustomerRequestModel, CustomerModel> {

}
