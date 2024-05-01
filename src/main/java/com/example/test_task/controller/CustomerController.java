package com.example.test_task.controller;

import com.example.test_task.mapper.CustomerMapper;
import com.example.test_task.request.CreateCustomerRequest;
import com.example.test_task.request.UpdateCustomerRequest;
import com.example.test_task.response.CustomerResponse;
import com.example.test_task.service.CustomerService;
import com.example.test_task.service.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerMapper mapper;
    private final CustomerService service;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> customerList(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toCustomerResponses(service.listAll()));
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toCustomerResponse(service.getById(id)));
    }

    @PostMapping("/customers")
    public  ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerRequest request){
        CustomerDto dto = mapper.toCustomerDto(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toCustomerResponse(service.create(dto)));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("id") Long id, @RequestBody UpdateCustomerRequest request){
        CustomerDto dto = mapper.toCustomerDto(id, request);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(mapper.toCustomerResponse(service.update(dto)));
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        service.delete(id);
    }
}
