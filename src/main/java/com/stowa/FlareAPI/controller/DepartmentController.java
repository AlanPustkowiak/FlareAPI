package com.stowa.FlareAPI.controller;

import com.stowa.FlareAPI.model.dto.ApiResponse;
import com.stowa.FlareAPI.model.dto.DepartmentDTO;
import com.stowa.FlareAPI.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin("*")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("id") Integer id){
        DepartmentDTO department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> addDepartment(@Valid @RequestBody DepartmentDTO department){
        DepartmentDTO newDepartment = departmentService.addDepartment(department);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @PathVariable("id") Integer id,
            @RequestBody DepartmentDTO department){

        departmentService.getDepartmentById(id);

        department.setId(id);
        DepartmentDTO updatedDepartment = departmentService.addDepartment(department);
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
