package com.gairola.department;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gairola.department.entity.Department;
import com.gairola.department.repository.DepartmentRepository;
import com.gairola.department.service.DepartmentServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DeptServiceTests {

    @Mock
    private DepartmentRepository repo;

    @InjectMocks
    private DepartmentServiceImpl service;

    private Department dept;

    @BeforeEach
    public void setup() {
        dept = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Delhi")
                .departmentCode("111")
                .build();
    }

    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        // given - precondition or setup
        given(repo.findById(dept.getDepartmentId()))
                .willReturn(Optional.empty());

        // given(repo.save(dept)).willReturn(dept);

        System.out.println(repo);
        System.out.println(service);

        // when - action or the behaviour that we are going test
        Department savedEmployee = service.saveDepartment(dept);

        System.out.println(savedEmployee);
        // then - verify the output
        // assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for deleteEmployee method
    @DisplayName("JUnit test for deleteEmployee method")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing() {
        // given - precondition or setup
        long Id = 1L;

        willDoNothing().given(repo).deleteById(Id);

        // when - action or the behaviour that we are going test
        // service.deleteDepartmentById(Id);

        // then - verify the output
        service.deleteDepartmentById(Id);
        verify(repo, times(1)).deleteById(Id);
    }
}