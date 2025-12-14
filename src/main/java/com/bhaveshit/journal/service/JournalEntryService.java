package com.bhaveshit.journal.service;

import com.bhaveshit.journal.entity.JournalEntry;
import com.bhaveshit.journal.entity.User;
import com.bhaveshit.journal.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void save(JournalEntry journalEntry , String username) {
        try {
            User user = userService.getByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            user.setUsername(null);
            userService.save(user);
        }catch(RuntimeException e) {
            throw new RuntimeException("Error saving journal entry" , e);
        }
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry getById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public void deleteById(String username, ObjectId id){
        User user = userService.getByUsername(username);
        user.getJournalEntries().removeIf(journalEntries ->journalEntries.getId().equals(id));
        userService.save(user);
        journalEntryRepository.deleteById(id);
    }

    public JournalEntry updateById(ObjectId id , JournalEntry newEntry) {

        JournalEntry old = journalEntryRepository.findById(id).orElse(null);
        if(old != null ){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());

        }
        return journalEntryRepository.save(old);
    }
}
