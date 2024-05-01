package com.example.test_task.service;

import com.example.test_task.data.entity.Customer;
import com.example.test_task.data.repository.CustomerRepository;
import com.example.test_task.exception.email.EmailContainsSpacesException;
import com.example.test_task.exception.phone.PhoneContainsSpacesException;
import com.example.test_task.exception.phone.StartsWithPhoneNumberException;
import com.example.test_task.mapper.CustomerMapper;
import com.example.test_task.service.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerMapper mapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerMapper mapper, CustomerRepository customerRepository) {
        this.mapper = mapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> listAll() {
        return mapper.toCustomerDtos(customerRepository.findAll());
    }

    @Override
    public CustomerDto create(CustomerDto customer) {
        if(!customer.getPhone().startsWith("+")){
            throw new StartsWithPhoneNumberException(customer.getPhone());
        }
        if(customer.getPhone().contains(" ")){
            throw new PhoneContainsSpacesException(customer.getPhone());
        }
        if(customer.getEmail().contains(" ")){
            throw new EmailContainsSpacesException(customer.getEmail());
        }
        Customer customerEntity = mapper.toCustomerEntity(customer);
        customerEntity.setId(null);
        customerEntity.setActive(true);
        Customer savedEntity = customerRepository.save(customerEntity);
        return mapper.toCustomerDto(savedEntity);
    }

    @Override
    public CustomerDto getById(Long id) {
        return mapper.toCustomerDto(customerRepository.getReferenceById(id));
    }

    @Override
    public CustomerDto update(CustomerDto customer) {
        Date date = new Date();

        if(!customer.getPhone().startsWith("+")){
            throw new StartsWithPhoneNumberException(customer.getPhone());
        }
        if(customer.getPhone().contains(" ")){
            throw new PhoneContainsSpacesException(customer.getPhone());
        }

        CustomerDto toUpdate = getById(customer.getId());
        customer.setUpdated(date.getTime());
        customer.setCreated(toUpdate.getCreated());
        customer.setActive(toUpdate.isActive());
        customer.setEmail(toUpdate.getEmail());
        customerRepository.save(mapper.toCustomerEntity(customer));

        return customer;
    }

    @Override
    public void delete(Long id) {
        customerRepository.makeInactive(id, false);
    }
}
