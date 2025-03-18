package com.stowa.FlareAPI.service;

import com.stowa.FlareAPI.exception.DepartmentNotFoundException;
import com.stowa.FlareAPI.model.entity.Department;
import com.stowa.FlareAPI.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService{
    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Integer id){
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Nie znaleziono departamentu!"));
    }

    public Department addDepartment(Department department){
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Integer id, Department department){
        getDepartmentById(id);
        department.setId(id);
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Integer id){
        getDepartmentById(id);
        departmentRepository.deleteById(id);
    }
}
