package com.saranya.microservice.todolist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saranya.microservice.todolist.Entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
      User findByUser(String userName);
}
