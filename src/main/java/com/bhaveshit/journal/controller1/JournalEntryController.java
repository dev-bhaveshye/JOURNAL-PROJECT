package com.bhaveshit.journal.controller1;

import com.bhaveshit.journal.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    HashMap<Long, JournalEntry> journalEntryHashMap = new HashMap<Long, JournalEntry>();

    @PostMapping
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalEntryHashMap.put(journalEntry.getId(), journalEntry);
        return true;
    }

    @GetMapping()
    public HashMap<Long, JournalEntry> getJournalEntryHashMap() {
        return journalEntryHashMap;
    }

    @GetMapping("getID/{ID}")
    public JournalEntry getJournalEntryByID(@PathVariable Long ID) {
        return journalEntryHashMap.get(ID);
    }

    @DeleteMapping("getID/{ID}")
    public boolean deleteJournalEntryByID(@PathVariable Long ID) {
        journalEntryHashMap.remove(ID);
        return true;
    }

    @PutMapping("getID/{ID}")
    public boolean updateJournalEntryByID(@PathVariable Long ID ,@RequestBody JournalEntry updatedEntry){
        journalEntryHashMap.put(ID, updatedEntry);
        return true;
    }
}