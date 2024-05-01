package com.example.test_task.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private Long created;
    private Long updated;
    private boolean isActive;
}
