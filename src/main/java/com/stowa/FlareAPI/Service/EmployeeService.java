package com.stowa.FlareAPI.Service;

import com.stowa.FlareAPI.exception.EmployeeNotFoundException;
import com.stowa.FlareAPI.model.entity.Department;
import com.stowa.FlareAPI.model.entity.Employee;
import com.stowa.FlareAPI.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository
    }

    public Employee addEmployee(Employee employee) {
        Department department = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono takiego departamentu"));
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        Department department = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono takiego departamentu"));
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id)
                .orElseThrow((() -> new EmployeeNotFoundException("User by id " + id + " was not found")));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }
}
