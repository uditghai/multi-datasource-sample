package com.sample.project.sample.controller.v1;

import com.sample.project.sample.model.CustomerModel;
import com.sample.project.sample.model.CustomerRequestModel;
import com.sample.project.sample.service.CustomerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(value = "Customer Controller", tags = {"Customer APIs"})
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerModel post(@RequestBody CustomerRequestModel request) {
        log.info("New Request for Customer Creation {}", request);
        return customerService.create(request);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerModel get(@PathVariable("id") String id) {
        log.info("Get customer details for id {}", id);
        return customerService.read(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerModel> get() {
        log.info("Get all customer details");
        return customerService.read();
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerModel put(@PathVariable("id") String id, @RequestBody CustomerRequestModel request) {
        log.info("Update customer {} with {}", id, request);
        return customerService.update(id, request);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerModel patch(@PathVariable("id") String id, @RequestBody CustomerRequestModel request) {
        log.info("patch customer {} with {}", id, request);
        return customerService.update(id, request);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        log.info("delete customer with id {}", id);
        customerService.delete(id);
    }

}
