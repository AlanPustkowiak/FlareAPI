package com.stowa.FlareAPI.model.dto;

import com.stowa.FlareAPI.model.entity.Department;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Integer id;

    @NotBlank(message = "Nazwa departamentu jest wymagana")
    private String name;

    private String description;

    public DepartmentDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.description = department.getDescription();
    }

    public Department toEntity() {
        Department department = new Department();
        department.setId(this.id);
        department.setName(this.name);
        department.setDescription(this.description);
        return department;
    }
}
