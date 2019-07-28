package com.saranya.microservice.todolist.Controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.FetchType;
import javax.sound.midi.Receiver;
import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.saranya.microservice.todolist.ToDoListApplication;
import com.saranya.microservice.todolist.Entity.ToDoList;
import com.saranya.microservice.todolist.Entity.User;
import com.saranya.microservice.todolist.Repository.ToDoListRepository;
import com.saranya.microservice.todolist.Repository.UserRepository;



@RestController
@Component
public class ToDoListController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ToDoListRepository toDoListRepository;
	
	@Autowired
    private RabbitTemplate template;
	
	
	//display all the user
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		
		return repository.findAll();
		
	}
	
	//display the user by id
	@GetMapping("/users/{id}")
	public Optional<User> retriveUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		if(!user.isPresent()) 
			throw new com.saranya.microservice.todolist.Exception.UserNotFoundException("Invalid id-"+id);	
		return user;
	}
	
	//create the user
	@PostMapping("/users/")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();		
	}
	
	//delete the user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	
	//Display the todolist
	@GetMapping("/users/{id}/todolist/")
	public List<ToDoList> retriveToDoList(@PathVariable int id){
		
		Optional<User> userOptional = repository.findById(id);
		if(!userOptional.isPresent()) 
			throw new com.saranya.microservice.todolist.Exception.UserNotFoundException("Invalid id-"+id);
		System.out.println(userOptional);
		return userOptional.get().getToDoList();
		}
	
	//Create the To-Do-List for the user ID
	@PostMapping("/users/{id}/todolist/")
	public ResponseEntity<Object> createToDoList(@PathVariable int id, @RequestBody ToDoList toDoList){
		Optional<User> currentUser = repository.findById(id);
		System.out.println(toDoList);
		User user = currentUser.get();
		toDoList.setUser(user);
		ToDoList savedList = toDoListRepository.save(toDoList);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(toDoList.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	// update the status and activity name
	
	@PostMapping("/users/{id}/todolist/{listId}")
	public void updateToDoList(@PathVariable int id,@PathVariable int listId, @RequestBody ToDoList toDoList){
		Optional<User> currentUser = repository.findById(id);
		Optional<ToDoList> currentListItem = toDoListRepository.findById(listId);
		toDoList.setId(listId);
		if(toDoList.getDescription() == null) {
			toDoList.setDescription(currentListItem.get().getDescription());
		}
		toDoList.setUser(currentUser.get());
		toDoListRepository.save(toDoList);
		Map<String, String> message = new HashMap<String, String>();
		String userName = currentUser.get().getUser();
		String firstName = currentUser.get().getFirstName();
		System.out.println(firstName);
		String statusMessage;
		if(toDoList.isStatus()==false) {
			statusMessage = "The Activity " + toDoList.getDescription()+" has been created";
		}
		else {
			statusMessage = "The Activity " + toDoList.getDescription()+" update to completed status";
		}
		message.put("firstName",firstName);
		message.put("userName",userName);
		message.put("statusMessage",statusMessage);
		this.template.convertAndSend(ToDoListApplication.topicExchangeName, "foo.bar.baz", message);
		
		
	}
	
	// Delete the item in the todolist
	
	@DeleteMapping("/users/{id}/todolist/{listId}")
	public void deleteListItem(@PathVariable int id,@PathVariable int listId) {
		
		System.out.println(listId);
		
		toDoListRepository.deleteById(listId);
		
	}
	
	//delete the entire todolist
	@DeleteMapping("/users/{id}/todolist")
	public void deleteList(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		toDoListRepository.deleteAll(user.get().getToDoList());	
	}
	

	
}
