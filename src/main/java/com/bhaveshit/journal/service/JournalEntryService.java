package com.bhaveshit.journal.service;

import com.bhaveshit.journal.entity.JournalEntry;
import com.bhaveshit.journal.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void save(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry getById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public void deleteById(ObjectId id){
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
