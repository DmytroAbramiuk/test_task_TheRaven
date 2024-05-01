package com.example.test_task.data.repository;

import com.example.test_task.data.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE customers SET is_active = :isActive WHERE id = :id", nativeQuery = true)
    void makeInactive(@Param("id") Long id, @Param("isActive") boolean isActive);
}
