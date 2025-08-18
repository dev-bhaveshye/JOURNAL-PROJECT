package com.bhaveshit.journal.repository;


import com.bhaveshit.journal.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {
}
