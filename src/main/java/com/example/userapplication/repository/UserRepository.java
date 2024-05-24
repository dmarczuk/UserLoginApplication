package com.example.userapplication.repository;

import com.example.userapplication.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, String> {

    Optional<MyUser> findByName(String name);
}
