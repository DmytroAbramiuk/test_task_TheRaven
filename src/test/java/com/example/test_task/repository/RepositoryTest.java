package com.example.test_task.repository;


import com.example.test_task.data.entity.Customer;
import com.example.test_task.data.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RepositoryTest {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void customerRepositoryMakeInactiveTest(){
        Customer customerEntity = new Customer();
        customerEntity.setFullName("Alice Abrams");
        customerEntity.setEmail("test.email@gmail.com");
        customerEntity.setPhone("+380631112222");
        customerEntity.setId(null);
        customerEntity.setActive(true);

        Customer savedEntity = repository.save(customerEntity);

        repository.makeInactive(savedEntity.getId(), false);

        entityManager.flush();
        entityManager.clear();

        Customer inactiveEntity = repository.getReferenceById(savedEntity.getId());

        Assertions.assertNotNull(inactiveEntity);
        Assertions.assertFalse(inactiveEntity.isActive());
    }
}
