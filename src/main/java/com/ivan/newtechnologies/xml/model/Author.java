package com.ivan.newtechnologies.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Author {

    @XmlElement(namespace = "https://ivan.com")
    private String firstname;

    @XmlElement(namespace = "https://ivan.com")
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }


    @Override
    public String toString() {
        return "Author{" +
                "firstname='" + this.firstname + '\'' +
                ", lastname='" + this.lastname + '\'' +
                '}';
    }
}
