package com.ivan.newtechnologies.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    @XmlElement(namespace = "https://ivan.com")
    private String name;

    @XmlElement(namespace = "https://ivan.com")
    private String description;

    @XmlElement(namespace = "https://ivan.com", name = "due-date")
    private String dueDate;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(final String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + this.name + '\'' +
                ", description='" + this.description + '\'' +
                ", dueDate='" + this.dueDate + '\'' +
                '}';
    }
}
