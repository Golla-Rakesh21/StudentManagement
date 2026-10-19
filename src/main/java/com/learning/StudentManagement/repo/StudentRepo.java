package com.learning.StudentManagement.repo;

import com.learning.StudentManagement.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Student> findByCourseIgnoreCase(String course, Pageable pageable);
    Page<Student> findByDepartmentIgnoreCase(String department, Pageable pageable);
}

