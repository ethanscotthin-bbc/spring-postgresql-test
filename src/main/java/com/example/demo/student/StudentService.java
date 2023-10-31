package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }



    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new IllegalStateException("student with id " + id + " does not exist");
        }
    }


    @Transactional
    public void updateStudent(Long studentID, String name, String email) {

        if (studentRepository.existsById(studentID)) {
            Student student = studentRepository.findById(studentID).orElseThrow(
                    () -> new IllegalStateException("Student does not exist.")
            );

            if (name != null && name.length() > 0 ) {
                student.setName(name);
            }
            Optional<Student> studentOptional = studentRepository.findByEmail(email);

            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            } else {
                student.setEmail(email);
            }
        } else {
            throw new IllegalStateException("student with id " + studentID + " does not exist");
        }

    }
}
