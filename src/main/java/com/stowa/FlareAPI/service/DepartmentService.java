package com.stowa.FlareAPI.service;

import com.stowa.FlareAPI.exception.DepartmentNotFoundException;
import com.stowa.FlareAPI.model.dto.DepartmentDTO;
import com.stowa.FlareAPI.model.entity.Department;
import com.stowa.FlareAPI.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService{
    private final DepartmentRepository departmentRepository;

    public List<DepartmentDTO> getAllDepartments(){
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentDTO::new)
                .collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Integer id){
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Nie znaleziono departamentu!"));
        return new DepartmentDTO(department);
    }

    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO){
        Department department = departmentDTO.toEntity();
        departmentRepository.save(department);
        return new DepartmentDTO(department);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO){
        getDepartmentById(departmentDTO.getId());
        Department department = departmentDTO.toEntity();
        departmentRepository.save(department);
        return new DepartmentDTO(department);
    }

    public void deleteDepartment(Integer id){
        getDepartmentById(id);
        departmentRepository.deleteById(id);
    }
}
