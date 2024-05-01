package com.example.test_task.service;

import com.example.test_task.service.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> listAll();
    CustomerDto create(CustomerDto customer);
    CustomerDto getById(Long id);
    CustomerDto update(CustomerDto customer);
    void delete(Long id);
}
