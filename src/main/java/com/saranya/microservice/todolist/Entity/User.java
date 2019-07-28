package com.saranya.microservice.todolist.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	private String user;
	private String password;
	private String firstName;
	private String secondName;

	@OneToMany(mappedBy = "user")
	private List<ToDoList> toDoList;
	
	public User() {
		
	}


	public User(int id, String user, String password, String firstName, String secondName, List<ToDoList> toDoList) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.firstName = firstName;
		this.secondName = secondName;
		this.toDoList = toDoList;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<ToDoList> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<ToDoList> toDoList) {
		this.toDoList = toDoList;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", user=" + user + ", password=" + password + ", toDoList=" + toDoList + "]";
	}
	
	


	
	
}
