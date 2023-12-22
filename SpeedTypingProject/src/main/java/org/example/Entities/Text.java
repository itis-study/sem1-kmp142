package org.example.Entities;

import java.util.UUID;

public class Text {
    String text;
    Category category;
    String id = UUID.randomUUID().toString();

    public Text(String text, Category category) {
        this.text = text;
        this.category = category;
    }

    public Text(){}

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(String id) {
        this.id = id;
    }
}
