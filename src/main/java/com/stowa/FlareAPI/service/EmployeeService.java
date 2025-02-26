package com.stowa.FlareAPI.service;

import com.stowa.FlareAPI.exception.EmployeeNotFoundException;
import com.stowa.FlareAPI.model.dto.EmployeeDTO;
import com.stowa.FlareAPI.model.entity.Department;
import com.stowa.FlareAPI.model.entity.Employee;
import com.stowa.FlareAPI.repository.DepartmentRepository;
import com.stowa.FlareAPI.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeDTO.toEntity();
        Department department = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono takiego departamentu"));
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(savedEmployee);
    }

    public List<EmployeeDTO> findAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeDTO.toEntity();
        Department department = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono takiego departamentu"));
        employee.setDepartment(department);
        Employee updatedEmployee = employeeRepository.save(employee);
        return new EmployeeDTO(updatedEmployee);
    }

    public EmployeeDTO findEmployeeById(Long id){
        Employee employee =  employeeRepository.findEmployeeById(id)
                .orElseThrow((() -> new EmployeeNotFoundException("User by id " + id + " was not found")));
        return new EmployeeDTO(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }
}
