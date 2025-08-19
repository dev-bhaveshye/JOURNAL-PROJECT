package com.bhaveshit.journal.controller;


import com.bhaveshit.journal.entity.JournalEntry;
import com.bhaveshit.journal.repository.JournalEntryRepository;
import com.bhaveshit.journal.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public boolean save(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
        return true;
    }
    @GetMapping
    public List<JournalEntry> getJournalEntry() {
        return journalEntryService.getAll();
    }




}
