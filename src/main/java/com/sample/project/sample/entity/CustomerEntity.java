package com.sample.project.sample.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    private String customerId;

    @Column(name = "customername", nullable = false)
    private String customerName;

    @Column(name = "customertype", nullable = false)
    private String customerType;

    @Column(name = "createdon", nullable = false)
    private LocalDate createdOn;

}
