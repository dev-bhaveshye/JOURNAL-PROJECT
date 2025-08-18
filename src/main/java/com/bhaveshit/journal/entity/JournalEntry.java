package com.bhaveshit.journal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private String id;

    private String title;

    private String content;

    private Date date;

    public Date getDate() {return date; }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setDate(Date date) {this.date = date; }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {this.title = title; }
}
