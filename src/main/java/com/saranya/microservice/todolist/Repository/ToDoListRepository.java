package com.saranya.microservice.todolist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saranya.microservice.todolist.Entity.ToDoList;



@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer>{

}
