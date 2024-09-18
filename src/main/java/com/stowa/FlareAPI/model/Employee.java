package com.stowa.FlareAPI.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String jobTitle;
    private Date dateOfBirth;
    private Integer departamentId;
    private String WorkLocation;

    public Employee() {}

    public Employee(String name, String email, String phone, String jobTitle,Date dateOfBirth, Integer departamentId, String WorkLocation) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.dateOfBirth = dateOfBirth;
        this.departamentId = departamentId;
        this.WorkLocation = WorkLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(Integer departamentId) {
        this.departamentId = departamentId;
    }

    public String getWorkLocation() {
        return WorkLocation;
    }

    public void setWorkLocation(String workLocation) {
        WorkLocation = workLocation;
    }
}
