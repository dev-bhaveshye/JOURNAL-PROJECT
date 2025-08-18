package com.bhaveshit.journal.controller;


import com.bhaveshit.journal.entity.JournalEntry;
import com.bhaveshit.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @PostMapping
    public boolean save(@RequestBody JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
        return true;
    }




}
