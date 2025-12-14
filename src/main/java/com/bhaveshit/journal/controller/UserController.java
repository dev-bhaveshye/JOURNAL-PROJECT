package com.bhaveshit.journal.controller;

import com.bhaveshit.journal.entity.User;
import com.bhaveshit.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<User> getById(@PathVariable ObjectId id){
        User data = userService.getById(id);
        if(data != null){
            return new ResponseEntity<>(data, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/username/{username}")
    public User getByUsername(@PathVariable String username){
        return userService.getByUsername(username);
    }

    @DeleteMapping
    public void deleteById(ObjectId id){
        userService.deleteById(id);
    }

    @PostMapping
    public User create(@RequestBody User user){
        userService.save(user);
        return user;
    }

    @PutMapping("/username/{username}")
    public ResponseEntity<User> update(@RequestBody User user , @PathVariable String username){
        try{
            User Entry = userService.getByUsername(username);
            if(Entry != null){
                Entry.setUsername(user.getUsername());
                Entry.setPassword(user.getPassword());
                User updated = userService.save(Entry);
                return new ResponseEntity<>(updated , HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
