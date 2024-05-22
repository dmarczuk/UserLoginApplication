package com.example.userapplication.model;

import jakarta.persistence.*;


@Entity
public class MyUser {
        @Id
        //@GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;
        //@Column(unique = true)
        private String name;
        private String password;
        private String email;

    public MyUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public MyUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
