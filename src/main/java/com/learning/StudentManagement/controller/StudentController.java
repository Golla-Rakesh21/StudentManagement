package com.learning.StudentManagement.controller;

import com.learning.StudentManagement.dto.StudentRequest;
import com.learning.StudentManagement.dto.StudentResponse;
import com.learning.StudentManagement.exception.ResourseNotFound;
import com.learning.StudentManagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Student Management", description = "Student REST APIs")
public class StudentController {

    @Autowired
    public StudentService serv;

    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent( @Valid @RequestBody StudentRequest request){

        StudentResponse response =serv.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<StudentResponse> getStudent(@Valid @PathVariable Long id) throws ResourseNotFound {

        StudentResponse response =serv.getStudentById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a student")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) throws ResourseNotFound {

        serv.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted");
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a student's complete details")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) throws ResourseNotFound {
        return ResponseEntity.ok(
                serv.updateStudent(id, request));
    }
    @GetMapping("/count")
    @Operation(summary = "Get the total number of students")
    public ResponseEntity<Long> getStudentCount() {
        return ResponseEntity.ok(
                serv.getStudentCount());
    }
    @GetMapping("/pagestudents")
    @Operation(summary = "List, search, sort and paginate students")
    public ResponseEntity<Page<StudentResponse>> getStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String course,
            @RequestParam(required = false) String department,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                serv.getStudents(
                        name, course, department,
                        page, size, sortBy, direction));
    }

}
