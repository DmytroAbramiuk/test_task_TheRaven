package com.example.test_task.mapper;

import com.example.test_task.data.entity.Customer;
import com.example.test_task.request.CreateCustomerRequest;
import com.example.test_task.request.UpdateCustomerRequest;
import com.example.test_task.response.CustomerResponse;
import com.example.test_task.service.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public List<CustomerDto> toCustomerDtos(Collection<Customer> entities){
        return entities.stream()
                .map(this::toCustomerDto)
                .collect(Collectors.toList());
    }

    public CustomerDto toCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setCreated(customer.getCreated());
        customerDto.setUpdated(customer.getUpdated());
        customerDto.setFullName(customer.getFullName());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customer.getEmail());
        customerDto.setActive(customer.isActive());

        return customerDto;
    }

    public Customer toCustomerEntity(CustomerDto dto){
        Customer customer = new Customer();

        customer.setId(dto.getId());
        customer.setCreated(dto.getCreated());
        customer.setUpdated(dto.getUpdated());
        customer.setFullName(dto.getFullName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setActive(dto.isActive());

        return customer;
    }

    public CustomerResponse toCustomerResponse(CustomerDto dto){
        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setId(dto.getId());
        customerResponse.setEmail(dto.getEmail());
        customerResponse.setPhone(dto.getPhone());
        customerResponse.setFullName(dto.getFullName());

        return customerResponse;
    }

    public List<CustomerResponse> toCustomerResponses(Collection<CustomerDto> dtos){
        return dtos.stream()
                .map(this::toCustomerResponse)
                .collect(Collectors.toList());
    }

    public CustomerDto toCustomerDto(CreateCustomerRequest request){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail(request.getEmail());
        customerDto.setFullName(request.getFullName());
        customerDto.setPhone(request.getPhone());
        return customerDto;
    }

    public CustomerDto toCustomerDto(Long id, UpdateCustomerRequest request){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(id);
        customerDto.setFullName(request.getFullName());
        customerDto.setPhone(request.getPhone());
        return customerDto;
    }
}
