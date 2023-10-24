package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {

            Student ethan = new Student(
                    "Ethan-Scott Hin",
                    LocalDate.of(2004, 10, 27),
                    "ethan-scott.hin@bbc.co.uk"

            );

            Student danny = new Student(
                    "Danny Rutter",
                    LocalDate.of(2000, 11, 22),
                    "danny.rutter@bbc.co.uk"

            );

            repository.saveAll(List.of(ethan, danny));
        };
    }
}
