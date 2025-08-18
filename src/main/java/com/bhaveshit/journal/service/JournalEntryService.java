package com.bhaveshit.journal.service;

import com.bhaveshit.journal.entity.JournalEntry;
import com.bhaveshit.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void save(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }
}
