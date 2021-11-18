package com.sample.project.sample.data;

import com.sample.project.sample.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    List<CustomerEntity> findByCustomerType(String customerType);
}
