package com.example.AttachNet.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")  // Use the "uuid2" generator to generate a random UUID
    @GenericGenerator(name = "uuid2", strategy = "uuid2")  // "uuid2" uses RFC-4122 for UUID generation
    private UUID uid;
    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;

    public User() {
    }

    public User(UUID uid, String name, String email, LocalDate dob, Integer age) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public User(String name, String email, LocalDate dob, Integer age) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public UUID getId() {
        return uid;
    }

    public void setId(UUID uid) {
        this.uid = uid;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + uid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
