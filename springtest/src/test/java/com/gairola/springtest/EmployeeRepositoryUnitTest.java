package com.gairola.springtest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.gairola.springtest.entity.Employee;
import com.gairola.springtest.repository.EmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryUnitTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test for save employee operation
    // @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail,com")
                .build();
        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);

    }

    // JUnit for findById student operation - BDD style
    @Test
    public void givenStudentId_whenfindbyId_thenReturnSavedStudent() {

        // given - setup or precondition
        Employee employee = Employee.builder().firstName("Ramesh")
                .lastName("fadatare").email("ramesh@gmail.com").build();
        Employee savedemployee = employeeRepository.save(employee);

        // when - action or the testing
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - very output
        assertThat(employee).isNotNull();
    }

}
