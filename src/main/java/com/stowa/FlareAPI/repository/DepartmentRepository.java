package com.stowa.FlareAPI.repository;

import com.stowa.FlareAPI.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findById(Integer id);
    Optional<Department> findByName(String name);
    List<Department> findByNameContainingIgnoreCase(String nameFragment);
}
