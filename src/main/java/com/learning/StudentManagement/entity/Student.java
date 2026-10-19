package com.learning.StudentManagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name="students",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_student_email",
                        columnNames = "email"
                )
        }
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 225)
    public String name;

    @Column(nullable = false,length = 150)
    public String email;

    @Column(nullable = false)
    public Integer age;

    @Column(nullable = false,length = 100)
    public String course;

    @Column(nullable = false,length = 100)
    public String department;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", course='" + course + '\'' +
                ", department='" + department + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public Student() {
    }

    public Student(String name, String email, Integer age, String course, String department) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
        this.department = department;
    }
    @PrePersist
    public void onCreate(){
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }
    @PreUpdate
    public void onupdate(){
        LocalDateTime now = LocalDateTime.now();
        updatedAt = now;
    }

}
