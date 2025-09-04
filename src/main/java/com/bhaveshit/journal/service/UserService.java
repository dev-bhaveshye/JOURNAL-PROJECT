package com.bhaveshit.journal.service;

import com.bhaveshit.journal.entity.JournalEntry;
import com.bhaveshit.journal.entity.User;
import com.bhaveshit.journal.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User userEntry) {
        try {
            userRepository.save(userEntry);
            return userEntry;
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(ObjectId id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

}
