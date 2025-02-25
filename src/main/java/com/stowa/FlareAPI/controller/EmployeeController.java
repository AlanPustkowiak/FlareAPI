package com.stowa.FlareAPI.controller;

import com.stowa.FlareAPI.Service.EmployeeService;
import com.stowa.FlareAPI.model.dto.ApiResponse;
import com.stowa.FlareAPI.model.dto.EmployeeDTO;
import com.stowa.FlareAPI.model.entity.Employee;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id) {
        EmployeeDTO employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO newEmployee = employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long id,@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        EmployeeDTO updateEmployee = employeeService.updateEmployee(employeeDTO);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        ApiResponse response = new ApiResponse(true, "Pracownik został pomyślnie usunięty");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@PathVariable Integer departmentId){
        List<EmployeeDTO> employees = employeeService.findemployeesByDepartment(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByStatus(@PathVariable Employee.EmployeeStatus status){
        List<EmployeeDTO> employees = employeeService.findEmployeesByStatus(status);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/hired-between")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesHiredBetween(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<EmployeeDTO> employees = employeeService.findEmployeesHiredBetween(startDate,endDate);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDTO>> searchEmployees(@RequestParam String query){
        List<EmployeeDTO> employees = employeeService.searchEmployees(query);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
