package com.stowa.FlareAPI.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotBlank(message = "Imię i nazwisko są wymagane")
    private String name;

    @Email(message = "Niepoprawny email")
    @NotBlank(message = "Email jest wymagany")
    @Column(unique = true)
    private String email;

    private String phone;

    @NotBlank(message = "Stanowisko jest wymagane")
    private String jobTitle;

    @Past(message = "Data urodzenia musi być w przeszłości")
    private LocalDate dateOfBirth;

    @NotNull(message = "ID departamentu jest wymagane")
    private Integer departamentId;

    private String WorkLocation;

    private LocalDate hireDate;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    public enum EmployeeStatus{
        ACTIVE, ON_LEAVE, TERMINATED
    }
}
