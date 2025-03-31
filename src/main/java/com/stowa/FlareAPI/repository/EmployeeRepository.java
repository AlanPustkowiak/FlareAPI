package com.stowa.FlareAPI.repository;

import com.stowa.FlareAPI.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Transactional
    void deleteEmployeeById(Long id);
    Optional<Employee> findEmployeeById(Long id);
}
