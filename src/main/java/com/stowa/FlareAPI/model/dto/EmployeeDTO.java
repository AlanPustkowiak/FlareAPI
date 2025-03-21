package com.stowa.FlareAPI.model.dto;

import com.stowa.FlareAPI.model.entity.Department;
import com.stowa.FlareAPI.model.entity.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Imię i nazwisko są wymagane")
    private String name;

    @Email(message = "Niepoprawny format adresu email")
    @NotBlank(message = "Email jest wymagany")
    private String email;

    private String phone;

    @NotBlank(message = "Stanowisko jest wymagane")
    private String jobTitle;

    @Past(message = "Data urodzenia musi być w przeszłości")
    private LocalDate dateOfBirth;

    @NotNull(message = "Departament jest wymagane")
    private DepartmentDTO department;

    private String workLocation;

    private LocalDate hireDate;

    private Employee.EmployeeStatus status;

    public EmployeeDTO(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.jobTitle = employee.getJobTitle();
        this.dateOfBirth = employee.getDateOfBirth();
        if (employee.getDepartment() != null) {
            this.department = new DepartmentDTO(employee.getDepartment());
        }
        this.workLocation = employee.getWorkLocation();
        this.hireDate = employee.getHireDate();
        this.status = employee.getStatus();
    }

    public Employee toEntity(){
        Employee emp = new Employee();
        emp.setId(this.id);
        emp.setName(this.name);
        emp.setEmail(this.email);
        emp.setPhone(this.phone);
        emp.setJobTitle(this.jobTitle);
        emp.setDateOfBirth(this.dateOfBirth);
        if (this.department != null) {
            Department department = new Department();
            department.setId(this.department.getId());
            emp.setDepartment(department);
        }
        emp.setWorkLocation(this.workLocation);
        emp.setHireDate(this.hireDate);
        emp.setStatus(this.status);
        return emp;
    }
}
