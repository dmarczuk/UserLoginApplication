package com.example.userapplication.model;

import jakarta.persistence.*;


@Entity
public class MyUser {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String name;
        @Column(nullable = false)
        private String password;
        @Column(nullable = false)
        private String email;

    public MyUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public MyUser() {
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
