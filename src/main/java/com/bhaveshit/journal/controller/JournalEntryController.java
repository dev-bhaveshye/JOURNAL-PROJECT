package com.bhaveshit.journal.controller;


import com.bhaveshit.journal.entity.JournalEntry;
import com.bhaveshit.journal.entity.User;
import com.bhaveshit.journal.repository.JournalEntryRepository;
import com.bhaveshit.journal.service.JournalEntryService;
import com.bhaveshit.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;


    @PostMapping("/username/{username}")
    public ResponseEntity<JournalEntry> save(@RequestBody JournalEntry journalEntry , @PathVariable String username) {
        try{
            journalEntryService.save(journalEntry , username);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("username/{username}")
    public ResponseEntity<?> getJournalEntriesByUsername(@PathVariable String username) {
        User user = userService.getByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();


        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId myId) {
        JournalEntry journalentry = journalEntryService.getById(myId);

        if(journalentry!=null) {
            return new ResponseEntity<>(journalentry, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<Object> deleteJournalEntry(@PathVariable ObjectId myId , @PathVariable String username) {
        journalEntryService.deleteById(username , myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> update(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry updatedEntry = journalEntryService.updateById(id, newEntry);
        return updatedEntry != null
                ? ResponseEntity.ok(updatedEntry)
                : ResponseEntity.notFound().build();
    }





}
