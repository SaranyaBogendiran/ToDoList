package com.saranya.microservice.todolist.Controller;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saranya.microservice.todolist.Entity.User;
import com.saranya.microservice.todolist.Repository.UserRepository;

@RestController
@CrossOrigin
public class UserController {
	UserRepository userRepository;
	
 
    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
    	
    	User loginUser = userRepository.findByUser(user.getUser());
        return
          user.getUser().equals(loginUser.getUser()) && user.getPassword().equals(loginUser.getPassword());
    }
     
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
          .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
          .decode(authToken)).split(":")[0];
    }
}