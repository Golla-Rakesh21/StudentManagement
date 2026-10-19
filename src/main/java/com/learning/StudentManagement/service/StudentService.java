package com.learning.StudentManagement.service;

import com.learning.StudentManagement.dto.StudentRequest;
import com.learning.StudentManagement.dto.StudentResponse;
import com.learning.StudentManagement.entity.Student;
import com.learning.StudentManagement.exception.ResourseNotFound;
import com.learning.StudentManagement.repo.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Transactional
public class StudentService {
    @Autowired
    public StudentRepo repo;

    public String normalize(String email){
        return email.trim().toLowerCase(Locale.ROOT);
    }

    private void applyRequest(
            Student student, StudentRequest request) {

        student.setName(request.name().trim());
        student.setEmail(normalize(request.email()));
        student.setAge(request.age());
        student.setCourse(request.course().trim());
        student.setDepartment(request.department().trim());
    }
    private StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getAge(),
                student.getCourse(),
                student.getDepartment(),
                student.getCreatedAt(),
                student.getUpdatedAt()
        );
    }

    public StudentResponse createStudent(StudentRequest request){
            String email=normalize(request.email());

            if(repo.existsByEmailIgnoreCase(email)){
                throw new IllegalArgumentException("A student with this email already exists");
            }
            Student student=new Student();
            applyRequest(student,request);
            student.setEmail(email);
        return toResponse(repo.save(student));
    }


    public StudentResponse getStudentById(Long id) throws ResourseNotFound {
        Student student=repo.findById(id).orElseThrow(()->new ResourseNotFound("the student by given id is not present in db"));
        return toResponse(student);
    }

    public Page<StudentResponse> getStudents(
            String name,
            String course,
            String department,
            int page,
            int size,
            String sortBy,
            String direction) {

        if (page < 0) {
            throw new IllegalArgumentException(
                    "Page number cannot be negative");
        }

        if (size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Page size must be between 1 and 100");
        }

        if (!"asc".equalsIgnoreCase(direction)
                && !"desc".equalsIgnoreCase(direction)) {
            throw new IllegalArgumentException(
                    "Sort direction must be asc or desc");
        }

        if (!"id".equals(sortBy)
                && !"name".equals(sortBy)
                && !"age".equals(sortBy)
                && !"course".equals(sortBy)
                && !"createdAt".equals(sortBy)) {
            throw new IllegalArgumentException(
                    "Unsupported sort field");
        }

        Sort.Direction sortDirection =
                Sort.Direction.fromString(direction);

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortDirection, sortBy));

        Page<Student> students;

        if (hasText(name)) {
            students = repo
                    .findByNameContainingIgnoreCase(
                            name.trim(), pageable);
        } else if (hasText(course)) {
            students = repo
                    .findByCourseIgnoreCase(
                            course.trim(), pageable);
        } else if (hasText(department)) {
            students = repo.findByDepartmentIgnoreCase(
                            department.trim(), pageable);
        } else {
            students = repo.findAll(pageable);
        }

        return students.map(this::toResponse);
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    public StudentResponse updateStudent(
            Long id, StudentRequest request) throws ResourseNotFound {

        Student student = findStudent(id);
        String email = normalize(request.email());

        if (repo.existsByEmailIgnoreCaseAndIdNot(
                email, id)) {
            throw new IllegalArgumentException(
                    "Another student already uses this email");
        }

        applyRequest(student, request);
        student.setEmail(email);

        return toResponse(repo.save(student));
    }

    public void deleteStudent(Long id) throws ResourseNotFound {
        Student student = findStudent(id);
        repo.delete(student);
    }

    private Student findStudent(Long id) throws ResourseNotFound{
        return repo.findById(id).orElseThrow(() -> new ResourseNotFound("Student not found with ID: " + id));
    }

    public long getStudentCount() {
        return repo.count();
    }


}
