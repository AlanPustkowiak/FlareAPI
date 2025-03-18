package com.stowa.FlareAPI.controller;

import com.stowa.FlareAPI.exception.DepartmentNotFoundException;
import com.stowa.FlareAPI.model.dto.ApiResponse;
import com.stowa.FlareAPI.model.entity.Department;
import com.stowa.FlareAPI.repository.DepartmentRepository;
import com.stowa.FlareAPI.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin("*")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(){
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Integer id){
        Department department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        Department newDepartment = departmentService.addDepartment(department);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable("id") Integer id,
            @RequestBody Department department){

        departmentService.getDepartmentById(id);

        department.setId(id);
        Department updatedDepartment = departmentService.addDepartment(department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable("id") Integer id){
        departmentService.getDepartmentById(id);
        departmentService.deleteDepartment(id);
        ApiResponse apiResponse = new ApiResponse(true, "Departament usunięty!");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
