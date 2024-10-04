package com.example.day1.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {
  // existsByFirstName
  boolean existsByFirstName(String firstName);
}
