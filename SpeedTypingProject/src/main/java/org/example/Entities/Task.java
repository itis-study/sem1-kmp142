package org.example.Entities;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private String id = UUID.randomUUID().toString();
    private Text text;
    private LocalDate creationDate = LocalDate.now();



    public Task(){

    }

    public Task(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}


