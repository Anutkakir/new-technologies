package com.ivan.newtechnologies.xml.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "todo-list", namespace = "https://ivan.com")
@XmlAccessorType(XmlAccessType.FIELD)
public class TodoList {

    @XmlElementWrapper(namespace = "https://ivan.com", name = "items")
    @XmlElement(namespace = "https://ivan.com", name = "item")
    private List<Item> items;

    @XmlElement(name = "author", namespace = "https://ivan.com")
    private Author author;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(final List<Item> items) {
        this.items = items;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "items=" + this.items +
                ", author=" + this.author +
                '}';
    }
}
